package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RuneScapeEndpoint;
import com.elypia.elypiai.runescape.data.RuneScapeSkill;
import com.elypia.elypiai.runescape.events.Level120Event;
import com.elypia.elypiai.runescape.events.LevelUpEvent;
import com.elypia.elypiai.runescape.events.MaxXpEvent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class RuneScape {

	public static final String PROFILE_PRIVATE = "PROFILE_PRIVATE";

	private RuneScape runescape;

	private Collection<RuneScapeUser> users;
	private Collection<RuneScapeListener> listeners;
	private ScheduledExecutorService service;

	public RuneScape() {
		users = new ArrayList<>();
		listeners = new ArrayList<>();
		runescape = this;
	}

	/**
	 * Return the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * but has their profile set to private, name will be "PROFILE_PRIVATE"
	 * and the rest of the object will be effectively null.
	 *
	 * @param username	The username of the player to get.
	 * @return			RuneScapePlayer
	 */

	public void getUser(String username, Consumer<RuneScapeUser> success, Consumer<UnirestException> failure) {
		Unirest.get(PROFILE_ENDPOINT).queryString("user", username).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				RuneScapeUser user = new RuneScapeUser(response.getBody().getObject());
				success.accept(user);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	/**
	 * Returns and caches the RuneScape player with the username provided.
	 * Possible null, if user doesn't exist. If the user does exist
	 * but has their profile set to private, name will be "PROFILE_PRIVATE"
	 * and the rest of the object will be effectively null. <br>
	 * Will not cache if null or PRIVATE. <br>
	 * Intended for leaderboard notifier system.
	 *
	 * @param username	The username of the player to get.
	 * @return			RuneScapePlayer
	 */

	public void cacheUser(String username) {
		getUser(username, result -> {
			if (!isPrivate(result))
				users.add(result);
		}, ex -> {
			ex.printStackTrace();
		});
	}

	/**
	 * @return If the player's profile was set to private.
	 */

	public boolean isPrivate(RuneScapeUser player) {
		if (player == null)
			return false;

		return (player.getUsername().equals(PROFILE_PRIVATE));
	}

	public void addListener(RuneScapeListener listener) {
		listeners.add(listener);

		if (service == null) {
			service = Executors.newSingleThreadScheduledExecutor();

			service.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					update();
				}
			}, 0, 2, TimeUnit.MINUTES);
		}
	}

	/**
	 * Disable the notifier system to stop receiving events.
	 */

	public void removeListener(RuneScapeListener listener) {
		listeners.remove(listener);
	}

	private void update() {
		users.forEach(user -> {
			Unirest.get(PROFILE_ENDPOINT).queryString("user", user.getUsername()).asJsonAsync(new Callback<JsonNode>() {

				@Override
				public void completed(HttpResponse<JsonNode> response) {
					Map<RuneScapeSkill, RuneScapeStat> stats = user.getStats();

					user.update(response.getBody().getObject());

					for (RuneScapeSkill skill : RuneScapeSkill.values()) {
						RuneScapeStat previous = stats.get(skill);
						RuneScapeStat current = user.getStat(skill);

						if (current.getLevel() > previous.getLevel()) {
							listeners.forEach(listener -> {
								listener.onLevelUp(new LevelUpEvent(user, current, previous));
							});
						}

						if (current.getVirtualLevel() == 120 && previous.getVirtualLevel() < 120) {
							listeners.forEach(listener -> {
								listener.onLevel120(new Level120Event(user, current));
							});
						}

						if (current.getXp() == 200000000 && previous.getXp() < 200000000) {
							listeners.forEach(listener -> {
								listener.onMaxXP(new MaxXpEvent(user, current, previous));
							});
						}
					}
				}

				@Override
				public void failed(UnirestException e) {
					// Background task, fail silently and update next time.
				}

				@Override
				public void cancelled() {

				}
			});
		});
	}

	public void getTotalUsers(Consumer<Integer> success, Consumer<UnirestException> failure) {
		Unirest.get(TOTAL_USERS).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				String result = response.getBody();
				result = result.substring(result.indexOf("{"), result.lastIndexOf("}") + 1);
				JSONObject object = new JSONObject(result);
				success.accept(object.getInt("accounts"));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	/**
	 * @return	Get the number of players currently online.
	 */

	public void getOnlineUserCount(Consumer<String> success, Consumer<UnirestException> failure) {
		Unirest.get(ONLINE_USERS).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				String result = response.getBody();
				result = result.substring(result.indexOf("(") + 1, result.lastIndexOf("}"));
				success.accept(result);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	public void getQuestStatuses(String user, Consumer<QuestsStatus> success, Consumer<UnirestException> failure) {
		String endpoint = RuneScapeEndpoint.RUNEMETRICS_QUESTS.getEndpoint();

		HttpRequest req = Unirest.get(endpoint).queryString("user", user);
		req.asJsonAsync(new Callback<JsonNode>( ) {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				JSONArray quests = object.getJSONArray("quests");

				QuestsStatus questsStatus = new QuestsStatus(runescape, user, quests);

				success.accept(questsStatus);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	/**
	 * @return	Get the list of cached players.
	 */

	public Collection<RuneScapeUser> getUsers() {
		return users;
	}

	/**
	 * @param	The xp to convert to level.
	 * @param	elite	Convert as an elite skill or standard skill.
	 * @return	Uses the RuneScape leveling system to convert
	 * 			the int provided (xp) to what level the user would
	 * 			have in a standard skill.
	 */

	public static int convertXpToLevel(int xp, boolean elite) {
		int level = 1;

		while (xp > convertLevelToXp(level, elite))
			level++;

		return level;
	}

	/**
	 * @param 	level	The level to convert to XP.
	 * @param	elite	Convert as an elite skill or standard skill.
	 * @return			Uses the RuneScape leveling system to convert
	 * 					the int provided (level) to the XP required
	 * 					to reach that level.
	 */

	public static int convertLevelToXp(int level, boolean elite) {
		double xp = 0;

		for (int count = 1; count < level; count++) {
			xp += (int)(count + 300 * Math.pow(2, (double)count / 7));

			// We return int so max value we can return is Integer.MAX_VALUE
			// thus if xp is greater than Integer.MAX_VALUE * 4
			// (* 4 because at the end of this equation we divide by 4)
			// No point doing any more since after casting the double (xp)
			// to an int it will always return what will equate to Integer.MAX_VALUE.
			// (Cast Integer.MAX_VALUE to double since int can't get bigger than there max! XD)

			if (xp >= (double)Integer.MAX_VALUE * 4)
				break;
		}

		return (int)(xp / 4);
	}
}
