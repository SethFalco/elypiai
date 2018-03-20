package com.elypia.elypiai.steam;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Steam {

	private final String API_KEY;

	private SteamRequester requester;

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param 	apiKey		API key obtained from Steam.
	 */

	public Steam(String apiKey) {
		API_KEY = Objects.requireNonNull(apiKey);
		requester = new SteamRequester(this);
	}

	public void getUser(String username, Consumer<SteamUser> success, Consumer<IOException> failure) {
		getIdFromVanityUrl(username, result -> {
			getUser(result, success, failure);
		}, failure);
	}

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param id Username of the player to get.
	 */

	public void getUser(long id, Consumer<SteamUser> success, Consumer<IOException> failure) {
		long[] ids = new long[] {id};

		getUsers(ids, result -> {
			SteamUser user = null;

			if (!result.isEmpty())
				user = result.iterator().next();

			success.accept(user);
		}, failure);
	}

	public void getUsers(long[] ids, Consumer<Collection<SteamUser>> success, Consumer<IOException> failure) {
		requester.getUsers(ids, success, failure);
	}

	/**
	 * Get the Steam users Library, do note the first time
	 * the method is called for each SteamProfile; it consumes
	 * another API call, the Library is cached however upon
	 * method call. This contains a list of games the steam user owns
	 * (or has played for free games) sorted from RecentPlaytime, and
	 * when RecentPlaytime is 0, from TotalPlaytime.
	 *
	 * @param user Steam user to obtain library for.
	 * @param success What to do with the result.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void getLibrary(SteamUser user, Consumer<List<SteamGame>> success, Consumer<IOException> failure) {
		requester.getLibrary(user, success, failure);
	}

	public void getIdFromVanityUrl(String vanityUrl, Consumer<Long> success, Consumer<IOException> failure) {
		requester.getIdFromVanityUrl(vanityUrl, success, failure);
	}

	public String getApiKey() {
		return API_KEY;
	}
}
