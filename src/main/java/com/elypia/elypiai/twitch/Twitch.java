package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.events.StreamerLiveEvent;
import com.elypia.elypiai.twitch.events.StreamerOfflineEvent;
import com.elypia.elypiai.utils.ElyUtils;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Twitch {

	public static final String USERS_ENDPOINT = "https://api.twitch.tv/kraken/users/";
	public static final String STREAMS_ENDPOINT = "https://api.twitch.tv/kraken/streams/";

	private final String API_KEY;

	private Map<Integer, TwitchUser> users;
	private List<TwitchListener> listeners;
	private ScheduledExecutorService service;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param 	apiKey		API key obtained from Twitch website.
	 */

	public Twitch(String apiKey) {
		API_KEY = apiKey;
		users = new HashMap<>();
		listeners = new ArrayList<>();
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

	public void addListener(TwitchListener listener) {
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

	public void removeListener(TwitchListener listener) {
		listeners.remove(listener);
	}

	public void update() {
		if (users.size() == 0)
			return;

		List<String> ids = users.keySet().stream().map(Object::toString).collect(Collectors.toList());
		List<List<String>> update = ElyUtils.splitList(ids, 100);

		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("limit", 100);

		update.forEach(list -> {
			queryParams.put("channel", String.join(",", list));
			Unirest.get(STREAMS_ENDPOINT).queryString(queryParams).headers(headers).asJsonAsync(new Callback<JsonNode>() {

				@Override
				public void completed(HttpResponse<JsonNode> response) {
					JSONObject object = response.getBody().getObject();
					JSONArray array = object.getJSONArray("streams");

					Map<Integer, JSONObject> online = new HashMap<>();

					for (int i = 0; i < array.length(); i++) {
						object = array.getJSONObject(i);
						online.put(object.getJSONObject("channel").getInt("_id"), object);
					}

					users.forEach((id, user) -> {
						TwitchStream stream = user.getStream();

						user.setStreamInfo(online.get(id));

						if (stream == null && user.getStream() != null) {
							listeners.forEach(listener -> {
								listener.onStreamerOffline(new StreamerOfflineEvent(user));
							});
						}

						else if (stream != null && user.getStream() == null) {
							listeners.forEach(listener -> {
								listener.onStreamerLive(new StreamerLiveEvent(user));
							});
						}
					});
				}

				@Override
				public void failed(UnirestException e) {
					// Fail silently and try again next time.
				}

				@Override
				public void cancelled() {

				}
			});
		});
	}

	/**
	 * @return 	All cached TWitch users.
	 */

	public Collection<TwitchUser> getTwitchUsers() {
		return users.values();
	}
}
