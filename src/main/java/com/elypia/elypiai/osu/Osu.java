package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMode;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ScheduledExecutorService;
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
	 * @param 	apiKey	The API obtained from the osu! website.
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
	 */

	public void getUser(String username, OsuMode mode, Consumer<OsuUser> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(USER_ENDPOINT);
		req.addParam("u", username);
		req.addParam("type", "string");

		getUser(mode, req, success, failure);
	}

	/**
	 * Calls the OSU API for the osuplayer by id.
	 *
	 * @param 	id		The players id.
	 * @param	mode	The gamemode to view data for.
	 */

	public void getUser(int id, OsuMode mode, Consumer<OsuUser> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(USER_ENDPOINT);
		req.addParam("u", id);
		req.addParam("type", "is");

		getUser(mode, req, success, failure);
	}

	private void getUser(OsuMode mode, ElyRequest req, Consumer<OsuUser> success, Consumer<IOException> failure) {
		req.addParam("k", API_KEY);
		req.addParam("m", mode.getId());

		req.get(result -> {
			JSONArray array = result.asJSONArray();
			OsuUser user = null;

			if (array.length() > 0) {
				JSONObject userObject = array.getJSONObject(0);
				user = new OsuUser(mode, userObject);
			}

			success.accept(user);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Get the all BeatMap information associated with a BeatMap id.
	 *
	 * @param	id	The id of the beatmap to search grab.
	 */

	public void getBeatMap(String id, Consumer<BeatMap> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(BEATMAP_ENDPOINT);
		req.addParam("k", API_KEY);
		req.addParam("b", id);
		req.addParam("limit", 1);

		req.get(result -> {
			JSONArray array = result.asJSONArray();
			JSONObject object = array.getJSONObject(0);
			BeatMap map = new BeatMap(object);

			success.accept(map);
		}, err -> {
			failure.accept(err);
		});
	}

	public void getRecentPlays(OsuUser user, int limit, Consumer<Collection<RecentPlay>> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(BEATMAP_ENDPOINT);
		req.addParam("k", API_KEY);
		req.addParam("u", user.getUserID());
		req.addParam("limit", limit);
		req.addParam("type", "id");

		req.get(result -> {
			Collection<RecentPlay> plays = new ArrayList<>();
			JSONArray array = result.asJSONArray();

			for (int i = 0; i < array.length(); i++) {
				JSONObject object = array.getJSONObject(i);

				RecentPlay play = new RecentPlay(object);
				plays.add(play);
			}

			success.accept(plays);
		}, err -> {
			failure.accept(err);
		});
	}
}
