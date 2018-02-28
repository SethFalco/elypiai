package com.elypia.elypiai.twitch;

import com.elypia.elypiai.ElyUtils;
import com.elypia.elypiai.twitch.events.StreamerLiveEvent;
import com.elypia.elypiai.twitch.events.StreamerOfflineEvent;
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
import java.util.stream.Collectors;

public class Twitch {

	public static final String USERS_ENDPOINT = "https://api.twitch.tv/kraken/users";
	public static final String STREAMS_ENDPOINT = "https://api.twitch.tv/kraken/streams";

	private Map<Integer, TwitchUser> users;
	private List<TwitchListener> listeners;
	private ScheduledExecutorService service;
	private Map<String, String> headers;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param 	apiKey		API key obtained from Twitch website.
	 * @return 				Twitch
	 */

	public Twitch(String apiKey) {
		users = new HashMap<>();
		listeners = new ArrayList<>();
		headers = new HashMap<>();
		headers.put("Accept", "application/vnd.twitchtv.v5+json");
		headers.put("Client-Id", apiKey);
	}

	/**
	 * Recieved Twitch information on a user, this only recieved general
	 * information.
	 *
	 * @param 	username	The username of the user to get.
	 * @return 				TwitchUser object with general channel info.
	 */

	public void getUserAsync(String username, Consumer<TwitchUser> success, Consumer<UnirestException> failure) {
		Unirest.get(USERS_ENDPOINT).queryString("login", username).headers(headers).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray users = response.getBody().getObject().getJSONArray("users");
				success.accept(new TwitchUser(users.getJSONObject(0)));
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

	public TwitchUser getUser(String username) throws UnirestException {
		HttpResponse<JsonNode> res = Unirest.get(USERS_ENDPOINT).queryString("login", username).headers(headers).asJson();
		JSONArray users = res.getBody().getObject().getJSONArray("users");
		return new TwitchUser(users.getJSONObject(0));
	}

	public void getUserAsync(int id, Consumer<TwitchUser> success, Consumer<UnirestException> failure) {
		Unirest.get(USERS_ENDPOINT + "/" + id).headers(headers).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				success.accept(new TwitchUser(object));
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

	public TwitchUser getUser(int id) throws UnirestException {
		HttpResponse<JsonNode> res = Unirest.get(USERS_ENDPOINT + "/" + id).headers(headers).asJson();
		JSONArray users = res.getBody().getObject().getJSONArray("users");
		return new TwitchUser(users.getJSONObject(0));
	}

	/**
	 * Recieved Twitch information on a user and caches the user,
	 * this only recieved general information.
	 * Intended for a notification system.
	 *
	 * @param 	username	The username of the user to get.
	 * @return 				TwitchUser object with general channel info.
	 */

	public void cacheUser(String username) {
		getUserAsync(username, result -> {
			users.put(result.getId(), result);
		}, ex -> {
			ex.printStackTrace();
		});
	}

	public void cacheUser(int id) {
		getUserAsync(id, result -> {
			users.put(result.getId(), result);
		}, ex -> {
			ex.printStackTrace();
		});
	}

	public void getUsers(Consumer<Collection<TwitchUser>> success, Consumer<UnirestException> failure, String...usernames) {
		Collection<TwitchUser> collection = new ArrayList<>();

		Unirest.get(USERS_ENDPOINT).queryString("login", String.join(",", usernames)).headers(headers).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray users = response.getBody().getObject().getJSONArray("users");

				for (int i = 0; i < users.length(); i++)
					collection.add(new TwitchUser(users.getJSONObject(i)));

				success.accept(collection);
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

	public void cacheUsers(String...usernames) {
		getUsers(result -> {
			result.forEach(o -> {
				users.put(o.getId(), o);
			});
		}, ex -> {
			ex.printStackTrace();
		}, usernames);
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
