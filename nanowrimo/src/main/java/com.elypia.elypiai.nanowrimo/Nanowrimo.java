package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.WordCountResponse;
import com.elypia.elypiai.nanowrimo.impl.INanowrimoService;
import com.elypia.elypiai.utils.okhttp.RestAction;
import org.apache.commons.codec.digest.DigestUtils;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

public class Nanowrimo {

	private static final String BASE_URL = "https://nanowrimo.org/";

	private INanowrimoService service;

	public Nanowrimo() {
		this(BASE_URL);
	}

	public Nanowrimo(String baseUrl) {
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());
		retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());

		service = retrofitBuilder.build().create(INanowrimoService.class);
	}

	public RestAction<NanoUser> getUser(String name) {
		return getUser(name, false);
    }

	public RestAction<NanoUser> getUser(String name, boolean history) {
		name = name.replace(" ", "-");
		Call<NanoUser> call = (history) ? service.getUserHistory(name) : service.getUser(name);
		return new RestAction<>(call);
	}

	public WordCountResponse setWordCount(String secret, String name, int wordCount) throws IOException {
		secret = DigestUtils.sha1Hex(secret + name + wordCount);
		Call<String> call = service.setWordCount(secret, name, wordCount);
		RestAction<String> action = new RestAction<>(call);
		String result = action.complete();

		// Until an event starts on there is documentation
		if (result != null)
			throw new RuntimeException("Seth! Come update this now that we get a positive result!");

        return WordCountResponse.get(action.getErrorBody());
	}
}
