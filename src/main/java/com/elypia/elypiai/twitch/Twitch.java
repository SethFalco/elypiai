package com.elypia.elypiai.twitch;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Twitch {

	private final String API_KEY;

	private TwitchRequester requester;

	private Map<Integer, TwitchUser> users;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param apiKey API key obtained from Twitch website.
	 */

	public Twitch(String apiKey) {
		API_KEY = Objects.requireNonNull(apiKey);
		requester = new TwitchRequester(this);
		users = new HashMap<>();
	}

	public void getUsers(String[] usernames, Consumer<Collection<TwitchUser>> success, Consumer<IOException> failure) {
		requester.getUsers(usernames, success, failure);
	}

	public String getApiKey() {
		return API_KEY;
	}

	/**
	 * @return 	All cached TWitch users.
	 */

	public Collection<TwitchUser> getTwitchUsers() {
		return users.values();
	}
}
