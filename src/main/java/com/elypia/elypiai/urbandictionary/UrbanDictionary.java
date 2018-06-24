package com.elypia.elypiai.urbandictionary;


import com.elypia.elypiai.utils.RetrofitUtils;
import retrofit2.*;

public class UrbanDictionary implements UrbanDictionaryService {

	private static final String BASE_URL = "http://api.urbandictionary.com/";

	private UrbanDictionaryService service;

	public UrbanDictionary() {
		this(BASE_URL);
	}

	public UrbanDictionary(String baseUrl) {
		service = RetrofitUtils.createService(baseUrl, UrbanDictionaryService.class);
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
