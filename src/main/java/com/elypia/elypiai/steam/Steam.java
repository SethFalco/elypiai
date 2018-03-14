package com.elypia.elypiai.steam;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Steam {

	public static final String GET_STEAM_ID = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/";
	public static final String GET_USER = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/";
	public static final String GET_LIB = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/";

	private final String API_KEY;

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param 	apiKey		API key obtained from Steam.
	 */

	public Steam(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param 	steamid	Username of the player to get.
	 */

	public void getUser(long steamid, Consumer<SteamUser> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(GET_USER);

		req.addParam("key", API_KEY);
		req.addParam("steamids", steamid);

		req.get(result -> {
			JSONArray players = result.asJSONObject().getJSONObject("response").getJSONArray("players");
			SteamUser user = new SteamUser(players.getJSONObject(0));
			success.accept(user);
		}, err -> {
			failure.accept(err);
		});
	}

	public void getUser(String username, Consumer<SteamUser> success, Consumer<IOException> failure) {
		getIdFromVanityUrl(username, result -> {
			getUser(result, success, failure);
		}, err -> {
			failure.accept(err);
		});
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
		ElyRequest req = new ElyRequest(GET_LIB);
		req.addParam("steamid", user.getSteamId());
		req.addParam("format", "json");
		req.addParam("key", API_KEY);
		req.addParam("include_appinfo", 1);
		req.addParam("include_played_free_games", 1);

		req.get(result -> {
			JSONArray array = result.asJSONObject().getJSONObject("response").getJSONArray("games");

			List<SteamGame> library = new ArrayList<>();

			for (int i = 0; i < array.length(); i++)
				library.add(new SteamGame(user, array.getJSONObject(i)));

			Collections.sort(library);
			success.accept(library);
		}, err -> {
			failure.accept(err);
		});
	}

	public void getIdFromVanityUrl(String vanityUrl, Consumer<Long> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(GET_STEAM_ID);
		req.addParam("key", API_KEY);
		req.addParam("vanityUrl", vanityUrl);

		req.get(result -> {
			JSONObject object = result.asJSONObject().getJSONObject("response");
			success.accept(object.getLong("steamid"));
		}, err ->{
			failure.accept(err);
		});
	}
}
