package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RSEndpoint;
import com.elypia.elypiai.runescape.data.RSSkill;
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
		String endpoint = RSEndpoint.RUNEMETRICS_PROFILE.getEndpoint();

	    Unirest.get(endpoint).queryString("user", username).asJsonAsync(new Callback<JsonNode>() {

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
        String endpoint = RSEndpoint.RUNEMETRICS_PROFILE.getEndpoint();

		users.forEach(user -> {
			Unirest.get(endpoint).queryString("user", user.getUsername()).asJsonAsync(new Callback<JsonNode>() {

				@Override
				public void completed(HttpResponse<JsonNode> response) {
					Map<RSSkill, RuneScapeStat> stats = user.getStats();

					user.update(response.getBody().getObject());

					for (RSSkill skill : RSSkill.values()) {
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

	/**
	 * @return	Get the number of players currently online.
	 */

	public void getOnlineUserCount(Consumer<String> success, Consumer<UnirestException> failure) {
        String endpoint = RSEndpoint.PLAYER_COUNT.getEndpoint();

		Unirest.get(endpoint).asStringAsync(new Callback<String>() {

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
		String endpoint = RSEndpoint.RUNEMETRICS_QUESTS.getEndpoint();

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
     * Convert XP to the level equivilent. <br>
     * Exactly the same as calling {@link #convertXpToLevel(int, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	xp      The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */

    public static int convertXpToLevel(long xp) {
        return convertXpToLevel(xp, false);
    }

    /**
     * Convert XP to the level equivilent.
     *
     * @param	xp      The xp to convert to level.
     * @param	elite	Convert as an elite skill or standard skill.
     * @return	        The level a player would be with the XP provided.
     */

	public static int convertXpToLevel(long xp, boolean elite) {
		if (xp < 0)
			throw new IllegalArgumentException("XP (long) can not be of a negative value.");

		int level = 1;
		long result;

		while (xp >= (result = convertLevelToXp(level + 1, elite))) {
			if (result == -1)
				break;

			level++;
		}

		return level;
	}

    /**
     * Convert a level, or virtual level to the XP equivilent using
     * RuneScapes XP formula. <br>
     * <strong</>Note: Returns -1 if the level is too high. <br>
     * Exactly the same as calling {@link #convertLevelToXp(int, boolean)},
     * with parameter boolean as <strong>false</strong>.
     *
     * @param	level   The xp to convert to level.
     * @return	        The level a player would be with the XP provided.
     */

    public static long convertLevelToXp(int level) {
        return convertLevelToXp(level, false);
    }

	/**
	 * Convert a level, or virtual level to the XP equivilent using
	 * RuneScapes XP formula. <br>
	 * Note: Returns -1 if the level is too high.
	 *
	 * @param   level	The level to convert to XP.
	 * @param	elite	Convert as an elite skill or standard skill.
	 * @return			The XP required to attain this level.
	 */

	public static long convertLevelToXp(int level, boolean elite) {
		if (level < 1)
			throw new IllegalArgumentException("Level (int) can not be zero or a negative value.");

		double xp = 0;

		for (int count = 1; count < level; count++) {
			xp += (long)(count + 300 * Math.pow(2, (double)count / 7));

			if (xp >= (double)Long.MAX_VALUE * 4)
				return -1;
		}

		return (long)(xp / 4);
	}
}
