package com.elypia.elypiai.urbandictionary;


import com.elypia.elypiai.utils.okhttp.deserializers.*;
import com.google.gson.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class UrbanDictionary implements UrbanDictionaryService {

	private static final String BASE_URL = "http://api.urbandictionary.com/";

	private UrbanDictionaryService service;

	public UrbanDictionary() {
		this(BASE_URL);
	}

	public UrbanDictionary(String baseUrl) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(UrbanResultType.class, new UrbanResultTypeDeserializer());
		gsonBuilder.registerTypeAdapter(String.class, new StringDeserializer());
		Gson gson = gsonBuilder.create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
		service = retrofit.create(UrbanDictionaryService.class);
	}

	/**
	 * Returns a defintion from Urban Dictionary if result(s)
	 * are found matching exactly the word or phrase provided. <br>
	 * Possible null: Returns null if no definitions are found.
	 *
	 * @param term The word or phrase to be defined.
	 */

	@Override
	public Call<UrbanResult> define(String term) {
		return service.define(term);
	}
}
