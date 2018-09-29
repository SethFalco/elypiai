package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.restutils.RestAction;
import com.elypia.elypiai.urbandictionary.impl.IUrbanDictionaryService;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrbanDictionary {

	private static final String BASE_URL = "http://api.urbandictionary.com/";

	private IUrbanDictionaryService service;

	public UrbanDictionary() {
		this(BASE_URL);
	}

	public UrbanDictionary(String baseUrl) {
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create());

		service = retrofitBuilder.build().create(IUrbanDictionaryService.class);
	}

	/**
	 * Returns a defintion from Urban Dictionary if result(s)
	 * are found matching exactly the word or phrase provided. <br>
	 * Possible null: Returns null if no definitions are found.
	 *
	 * @param term The word or phrase to be defined.
	 */
	public RestAction<UrbanResult> define(String term) {
		Call<UrbanResult> call = service.define(term);
		return new RestAction<>(call);
	}
}
