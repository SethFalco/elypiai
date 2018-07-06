package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.utils.okhttp.RestAction;
import com.elypia.elypiai.yugioh.impl.*;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class YuGiOh {

    private static final String BASE_URL = "http://yugiohprices.com/api/";

    private IYuGiOhService service;

	public YuGiOh() {
		this(BASE_URL);
    }

    public YuGiOh(String baseUrl) {
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(GsonConverterFactory.create());

		service = retrofitBuilder.build().create(IYuGiOhService.class);
    }

	/**
	 * Search the YuGiOh prices API for the YuGiOh card requested.
	 * This only returns a single card and the spelling of the card
	 * must be perfect, including hythens (-) and spacing.
	 * Can return a monster or spell/trap card.
	 *
	 * @param name The YuGiOh card to search up, must match card name exactly.
	 */

    public RestAction<? extends TradingCard> getCard(String name) {
		Call<? extends TradingCard> call = service.getCard(name);
		return new RestAction<>(call);
	}
}
