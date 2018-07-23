package com.elypia.elypiai.dailymotion;

import com.elypia.elypiai.dailymotion.deserializer.DailymotionVideoDeserializer;
import com.elypia.elypiai.dailymotion.impl.IDailymotionService;
import com.elypia.elypiai.utils.okhttp.RestAction;
import com.google.gson.GsonBuilder;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dailymotion {

	private static final String BASE_URL = "https://api.dailymotion.com/";

	private IDailymotionService service;

	public Dailymotion() {
		this(BASE_URL);
	}

	public Dailymotion(String baseUrl) {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(DailymotionVideo.class, new DailymotionVideoDeserializer(this));

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create(builder.create()));

		service = retrofitBuilder.build().create(IDailymotionService.class);
	}

	public RestAction<DailymotionVideo> getVideo(String videoId) {
		Call<DailymotionVideo> call = service.getVideo(videoId);
		return new RestAction<>(call);
	}
}
