package com.elypia.elypiai.twitch;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Twitch {

	public static final String USERS_ENDPOINT = "https://api.twitch.tv/kraken/users/";
	public static final String STREAMS_ENDPOINT = "https://api.twitch.tv/kraken/streams/";

	private final String API_KEY;

	private Map<Integer, TwitchUser> users;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param apiKey API key obtained from Twitch website.
	 */

	public Twitch(String apiKey) {
		API_KEY = apiKey;
		users = new HashMap<>();
	}

	/**
	 * Recieved Twitch information on a user, this only recieved general
	 * information.
	 *
	 * @param 	username	The username of the user to get.
	 */

	public void getUser(String username, Consumer<TwitchUser> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(USERS_ENDPOINT);
		req.addParam("login", username);
		req.addHeader("Accept", "application/vnd.twitchtv.v5+json");
		req.addHeader("Client-Id", API_KEY);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			JSONArray users = object.getJSONArray("users");
			JSONObject user = users.getJSONObject(0);
			TwitchUser twitchUser = new TwitchUser(user);

			success.accept(twitchUser);
		}, err -> {
			failure.accept(err);
		});
	}

	public void getUser(int id, Consumer<TwitchUser> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(USERS_ENDPOINT + "/%s", id);
		req.addHeader("Accept", "application/vnd.twitchtv.v5+json");
		req.addHeader("Client-Id", API_KEY);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			TwitchUser user = new TwitchUser(object);

			success.accept(user);
		}, err -> {
			failure.accept(err);
		});
	}

	public void getUsers(String[] usernames, Consumer<Collection<TwitchUser>> success, Consumer<IOException> failure) {
		String param = String.join(",", usernames);

		ElyRequest req = new ElyRequest(USERS_ENDPOINT);
		req.addParam("login", param);
		req.addHeader("Accept", "application/vnd.twitchtv.v5+json");
		req.addHeader("Client-Id", API_KEY);

		req.get(result -> {
			Collection<TwitchUser> users = new ArrayList<>();
			JSONObject object = result.asJSONObject();
			JSONArray usersArray = object.getJSONArray("users");

			for (int i = 0; i < usersArray.length(); i++) {
				JSONObject userObject = usersArray.getJSONObject(i);
				TwitchUser user = new TwitchUser(userObject);
				users.add(user);
			}

			success.accept(users);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * @return 	All cached TWitch users.
	 */

	public Collection<TwitchUser> getTwitchUsers() {
		return users.values();
	}
}
