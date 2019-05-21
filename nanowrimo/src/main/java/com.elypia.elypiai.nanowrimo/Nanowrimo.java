package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.common.RestAction;
import com.elypia.elypiai.nanowrimo.impl.INanowrimoService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;

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
}
