package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.restutils.RestAction;
import com.elypia.elypiai.urbandictionary.impl.IUrbanDictionaryService;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;

public class UrbanDictionary {

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("http://api.urbandictionary.com/");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	private IUrbanDictionaryService service;

	public UrbanDictionary() {
		this(BASE_URL);
	}

	public UrbanDictionary(URL url) {
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(url.toString());
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
