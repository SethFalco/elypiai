package com.elypia.elypiai.steam;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
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
	 * @param 	api_key		API key obtained from Steam.
	 * @return				Steam object.
	 */

	public Steam(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param 	username	Username of the player to get.
	 * @return				SteamProfile, containing all user information.
	 */

	public void getUser(long steamid, Consumer<SteamUser> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("steamids", steamid);
	}

	public void getUser(String username, Consumer<SteamUser> success, Consumer<UnirestException> failure) {
		getIdFromVanityUrl(username, result -> {
			Map<String, Object> queryParams = new HashMap<>();
			queryParams.put("key", API_KEY);
			queryParams.put("steamids", result);

			Unirest.get(GET_USER).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

				@Override
				public void completed(HttpResponse<JsonNode> response) {
					JSONArray players = response.getBody().getObject().getJSONObject("response").getJSONArray("players");
					SteamUser user = new SteamUser(players.getJSONObject(0));
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
		}, ex -> {
			failure.accept(ex);
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
	 * @return	List of {@link SteamGame}s the SteamUser owns.
	 */

	public void getLibrary(SteamUser user, Consumer<List<SteamGame>> success, Consumer<UnirestException> failure) {
		try {

		List<SteamGame> library = new ArrayList<>();

		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("steamid", user.getSteamId());
		queryParams.put("format", "json");
		queryParams.put("key", API_KEY);
		queryParams.put("include_appinfo", 1);
		queryParams.put("include_played_free_games", 1);

		Unirest.get(GET_LIB).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray array = response.getBody().getObject().getJSONObject("response").getJSONArray("games");

				for (int i = 0; i < array.length(); i++)
					library.add(new SteamGame(user, array.getJSONObject(i)));

				Collections.sort(library);
				success.accept(library);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void getIdFromVanityUrl(String vanityUrl, Consumer<String> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("vanityUrl", vanityUrl);

		Unirest.get(GET_STEAM_ID).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject().getJSONObject("response");
				success.accept(object.getString("steamid"));
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
}