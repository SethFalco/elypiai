package com.elypia.elypiai.sightengine;

import com.elypia.elypiai.sightengine.deserializers.NudityScoreDeserializer;
import com.elypia.elypiai.sightengine.impl.ISightEngineService;
import com.elypia.elypiai.utils.okhttp.RestAction;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class SightEngine {

	private static final String BASE_URL = "https://api.sightengine.com/1.0/";

	private final String USER;
	private final String SECRET;

	private ISightEngineService service;

	/**
	 * The SigthtEngine API can detect nudity in images
	 * provided, as well as has facial recognistion, mood
	 * recognition and other functions.
	 *
	 * @param user		The user name from the SightEnginer API.
	 * @param secret	The Secret from the SightEngine API.
	 */

	public SightEngine(String user, String secret) {
		this(BASE_URL, user, secret);
	}

	public SightEngine(String baseUrl, String user, String secret) {
		USER = user;
		SECRET = secret;

		OkHttpClient client = new OkHttpClient.Builder().addInterceptor((chain) -> {
			Request request = chain.request();
			HttpUrl url = request.url().newBuilder().addQueryParameter("api_user", user).addQueryParameter("api_secret", secret).build();
			request = request.newBuilder().url(url).build();
			return chain.proceed(request);
		}).build();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(NudityScore.class, new NudityScoreDeserializer());
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));

		service = retrofitBuilder.client(client).build().create(ISightEngineService.class);
	}

	/**
	 * URL should be of an image, API checks the image
	 * and returns information on it, such as if the image is safe
	 * with a score from 0 to 1, of how safe it is, tags of what
	 * makes it unsafe.
	 *
	 * @param 	url		The url of an image to check for.
	 */

	public RestAction<NudityScore> detectNudity(String url) {
		Call<NudityScore> call = service.detectNudity(url);
		return new RestAction<>(call);
	}

	public String getUser() {
		return USER;
	}

	public String getSecret() {
		return SECRET;
	}
}
