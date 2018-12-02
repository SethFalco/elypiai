package com.elypia.elypiai.twitch;

import com.elypia.elypiai.restutils.RestAction;
import com.elypia.elypiai.twitch.deserializers.*;
import com.elypia.elypiai.twitch.impl.ITwitchService;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;

public class Twitch {

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://api.twitch.tv/helix/");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	private final String CLIENT_ID;

	private ITwitchService service;

	/**
	 * Allows calls to the Twitch API, can call various information
	 * on users, or get stream information if the user is live.
	 *
	 * @param apiKey API key obtained from Twitch website.
	 */
	public Twitch(String apiKey) {
		this(BASE_URL, apiKey);
	}

	public Twitch(URL baseUrl, String apiKey) {
		Objects.requireNonNull(baseUrl);
		CLIENT_ID = Objects.requireNonNull(apiKey);

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor((chain) -> {
			Request.Builder builder = chain.request().newBuilder();
			Request request = builder.header("Client-Id", CLIENT_ID).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ssZ");
		gsonBuilder.registerTypeAdapter(new TypeToken<List<User>>(){}.getType(), new TwitchUserDeserializer(this));

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl.toString());
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.client(client).build().create(ITwitchService.class);
	}

	public RestAction<List<User>> getUsers(TwitchQuery query) {
		if (!query.getGames().isEmpty())
			throw new IllegalArgumentException("Can not query users by game id.");

		if (query.getTotal() > 100)
			throw new IllegalArgumentException("Can not query over 100 entities at once.");

		Call<List<User>> call = service.getUsers(query.getUserIds(), query.getUsernames());
		return new RestAction<>(call);
	}

	public StreamPaginator getStreams(TwitchQuery query, int limit) {
		if (limit > 100)
			throw new IllegalArgumentException("Can not retrieve more than 100 users per page.");

		return new StreamPaginator(this, query, limit);
	}

	public String getClientId() {
		return CLIENT_ID;
	}

	protected ITwitchService getService() {
		return service;
	}
}
