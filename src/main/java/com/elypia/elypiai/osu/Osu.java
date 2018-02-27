package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMode;
import com.elypia.elypiai.osu.events.LevelUpEvent;
import com.elypia.elypiai.osu.events.PPUpEvent;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Osu {

	public static final String USER_ENDPOINT = "https://osu.ppy.sh/api/get_user";
	public static final String BEATMAP_ENDPOINT = "https://osu.ppy.sh/api/get_beatmaps";
	public static final String RECENT_PLAY_ENDPOINT = "https://osu.ppy.sh/api/get_user_recent";

	private final String API_KEY;

	private Collection<OsuUser> users;
	private Collection<OsuListener> listeners;
	private ScheduledExecutorService service;

	/**
	 * Creates an OSU object for making calls to the osu API.
	 * Using this you can call information on each user, as well
	 * as beatmaps, and recentplay data for users.
	 *
	 * @param 	apikey	The API obtained from the osu! website.
	 * @return 			OSU object
	 */

	public Osu(String apiKey) {
		API_KEY = apiKey;
		users = new ArrayList<>();
		listeners = new ArrayList<>();
	}

	/**
	 * Calls the OSU API for the osuplayer by username.
	 *
	 * @param 	username	The players username.
	 * @param	mode		The gamemode to view data for.
	 * @return 				OSUPlayer, contains all information on the user.
	 */

	public void getUser(String username, OsuMode mode, Consumer<OsuUser> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("u", username);
		queryParams.put("type", "string");

		getUser(mode, queryParams, success, failure);
	}

	/**
	 * Calls the OSU API for the osuplayer by id.
	 *
	 * @param 	id		The players id.
	 * @param	mode	The gamemode to view data for.
	 * @return 			OSUPlayer, contains all information on the user.
	 */

	public void getUser(int id, OsuMode mode, Consumer<OsuUser> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("u", id);
		queryParams.put("type", "id");

		getUser(mode, queryParams, success, failure);
	}

	private void getUser(OsuMode mode, Map<String, Object> queryParams, Consumer<OsuUser> success, Consumer<UnirestException> failure) {
		queryParams.put("k", API_KEY);
		queryParams.put("m", mode.getId());

		Unirest.get(USER_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray array = response.getBody().getArray();
				OsuUser user = array.length() > 0 ? new OsuUser(mode, array.getJSONObject(0)) : null;
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
	 * Calls the OSU API for the osuplayer by username and caches
	 * the data internally for later use. Primarily for local
	 * leaderboards or a notifier system. If user is not needed
	 * again see: {@link #getUser(String, int)}.
	 *
	 * @param 	username	The players username.
	 * @param	mode		The gamemode to view data for.
	 * @return 				OSUPlayer, contains all information on the user.
	 */

	public void cacheUser(String username, OsuMode mode) {
		getUser(username, mode, result -> {
			users.add(result);
		}, ex -> {
			ex.printStackTrace();
		});
	}

	/**
	 * Calls the OSU API for the osuplayer by id and caches
	 * the data internally for later use. Primarily for local
	 * leaderboards or a notifier system. If user is not needed
	 * again see: {@link #getUser(int, int)}.
	 *
	 * @param 	id		The players id.
	 * @param	mode	The gamemode to view data for.
	 * @return 			OSUPlayer, contains all information on the user.
	 */

	public void cacheUser(int id, OsuMode mode) {
		getUser(id, mode, result -> {
			users.add(result);
		}, ex -> {
			ex.printStackTrace();
		});
	}

	/**
	 * See {@link #initiateNotifier(double)}
	 * Add the listener of an object implementing the OSUListener
	 * interface, this allows events to execute when a user gains PP.
	 */

	public void addListener(OsuListener listener) {
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

	public void removeListener(OsuListener listener) {
		listeners.remove(listener);
	}

	public void update() {
		users.forEach(user -> {
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("u", user.getUserID());
			queryParams.put("type", "id");
			queryParams.put("k", API_KEY);
			queryParams.put("m", user.getGameMode().getId());

			Unirest.get(USER_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

				@Override
				public void completed(HttpResponse<JsonNode> response) {
					int level = (int)user.getLevel();
					double pp = user.getPP();
					int rank = user.getLeaderboardRank();

					user.update(response.getBody().getArray().getJSONObject(0));

					if (user.getLevel() > level) {
						listeners.forEach(listener -> {
							listener.onLevelUp(new LevelUpEvent(user, level));
						});
					}

					if (user.getPP() > pp) {
						listeners.forEach(listener -> {
							listener.onPPUp(new PPUpEvent(user, pp, rank));
						});
					}
				}

				@Override
				public void failed(UnirestException e) {
					// This is a background task - fail silently and update next time.
				}

				@Override
				public void cancelled() {

				}
			});
		});
	}

	/**
	 * Get the all BeatMap information associated with a RecentPlay.
	 *
	 * @param	play	RecentPlay object from an OSUPlayer
	 * @return 			BeatMap, contains all information on the BeatMap.
	 */

	public void getBeatMap(RecentPlay play, Consumer<BeatMap> success, Consumer<UnirestException> failure) {
		getBeatMap(play.getBeatMapID(), success, failure);
	}

	/**
	 * Get the all BeatMap information associated with a BeatMap id.
	 *
	 * @param	id	The id of the beatmap to search grab.
	 * @return 		BeatMap, contains all information on the BeatMap.
	 */

	public void getBeatMap(String id, Consumer<BeatMap> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("k", API_KEY);
		queryParams.put("b", id);
		queryParams.put("limit", 1);

		Unirest.get(BEATMAP_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				BeatMap map = new BeatMap(response.getBody().getArray().getJSONObject(0));
				success.accept(map);
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

	public void getRecentPlays(OsuUser user, int limit, Consumer<List<RecentPlay>> success, Consumer<UnirestException> failure) {
		List<RecentPlay> plays = new ArrayList<>();
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("k", API_KEY);
		queryParams.put("u", user.getUserID());
		queryParams.put("limit", limit);
		queryParams.put("type", "id");

		Unirest.get(RECENT_PLAY_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray array = response.getBody().getArray();

				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					plays.add(new RecentPlay(object));
				}

				success.accept(plays);
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

	public Collection<OsuUser> getCachedUsers() {
		return users;
	}
}