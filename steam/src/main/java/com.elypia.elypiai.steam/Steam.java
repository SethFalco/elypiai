package com.elypia.elypiai.steam;

import com.elypia.elypiai.steam.deserializers.*;
import com.elypia.elypiai.steam.impl.ISteamService;
import com.elypia.elypiai.utils.okhttp.RestAction;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;

public class Steam {

	private final static String BASE_URL = "http://api.steampowered.com/";

	private final String API_KEY;

	private ISteamService service;

	/**
	 * The Steam API allows calls to basic Steam information
	 * as well as user information such as query the inventory
	 * or obtaining stats.
	 *
	 * @param 	apiKey		API key obtained from Steam.
	 */

	public Steam(String apiKey) {
		this(BASE_URL, apiKey);
	}

	public Steam(String baseUrl, String apiKey) {
		API_KEY = Objects.requireNonNull(apiKey);

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor((chain) -> {
			Request request = chain.request();
			HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).build();
			request = request.newBuilder().url(url).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(SteamSearch.class, new SteamSearchDeserializer());
		gsonBuilder.registerTypeAdapter(new TypeToken<List<SteamGame>>(){}.getType(), new SteamGameDeserializer());
		gsonBuilder.registerTypeAdapter(new TypeToken<List<SteamUser>>(){}.getType(), new SteamUserDeserializer(this));

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.client(client).build().create(ISteamService.class);
	}

	public RestAction<SteamSearch> getIdFromVanityUrl(String vanityUrl) {
		Call<SteamSearch> call = service.getSteamId(vanityUrl);
		return new RestAction<>(call);
	}

	public RestAction<List<SteamUser>> getUsers(long...ids) {
		if (ids == null || ids.length == 0)
			throw new IllegalArgumentException("Must specify at least one user to fetch.");

		StringJoiner joiner = new StringJoiner(",");

		for (long id : ids)
			joiner.add(String.valueOf(id));

		Call<List<SteamUser>> call = service.getUsers(joiner.toString());
		return new RestAction<>(call);
	}

	/**
	 * Get the Steam users Library, do note the first time
	 * the method is called for each SteamProfile; it consumes
	 * another API call, the Library is cached however upon
	 * method call. This contains a list of games the steam user owns
	 * (or has played for free games) sorted from RecentPlaytime, and
	 * when RecentPlaytime is 0, from TotalPlaytime.
	 *
	 * @param id Steam user to obtain library for.
	 */

	public RestAction<List<SteamGame>> getLibrary(long id) {
		return getLibrary(id, true);
	}

	public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames) {
		return getLibrary(id, freeGames, true);
	}

	public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames, boolean info) {
		Call<List<SteamGame>> call = service.getLibrary(id, freeGames ? 1 : 0, info ? 1 : 0);
		return new RestAction<>(call);
	}

	public String getApiKey() {
		return API_KEY;
	}
}
