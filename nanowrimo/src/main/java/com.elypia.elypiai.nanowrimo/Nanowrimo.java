package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.WordCountResponse;
import com.elypia.elypiai.nanowrimo.impl.INanowrimoService;
import com.elypia.elypiai.restutils.RestAction;
import org.apache.commons.codec.digest.DigestUtils;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.net.*;

public class Nanowrimo {

	/**
	 * The default URL we call too. <br>
	 * Should never throw {@link MalformedURLException} as this
	 * is a manually hardcoded URL.
	 */
	private static URL BASE_URL;

	static {
		try {
			BASE_URL = new URL("https://nanowrimo.org/");
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}
	}

	private INanowrimoService service;

	public Nanowrimo() {
		this(BASE_URL);
	}

	public Nanowrimo(URL baseUrl) {
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl.toString());
		retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());
		retrofitBuilder.addConverterFactory(ScalarsConverterFactory.create());

		service = retrofitBuilder.build().create(INanowrimoService.class);
	}

	public RestAction<Writer> getUser(String name) {
		return getUser(name, false);
    }

	public RestAction<Writer> getUser(String name, boolean history) {
		String requestName = name.replace(" ", "-");
		Call<Writer> call = (history) ? service.getUserHistory(requestName) : service.getUser(requestName);
		return new RestAction<>(call);
	}

	public WordCountResponse setWordCount(String secret, String name, int wordCount) throws IOException {
		String encodedSecret = DigestUtils.sha1Hex(secret + name + wordCount);
		Call<String> call = service.setWordCount(encodedSecret, name, wordCount);
		RestAction<String> action = new RestAction<>(call);
		String result = action.complete();

		if (result != null)
			throw new IllegalStateException("This is an unknown state due to poor API documentation.");

        return WordCountResponse.get(action.getErrorBody());
	}
}
