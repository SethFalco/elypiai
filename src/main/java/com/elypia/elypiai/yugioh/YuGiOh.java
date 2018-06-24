package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.utils.RetrofitUtils;
import retrofit2.Call;

public class YuGiOh implements YuGiOhService {

    private static final String BASE_URL = "http://yugiohprices.com/";

    private YuGiOhService service;

	public static final String IMAGE = "http://yugiohprices.com/api/card_image/%s";

	public YuGiOh() {
        this(BASE_URL);
    }

    public YuGiOh(String baseUrl) {
        service = RetrofitUtils.createService(baseUrl, YuGiOhService.class);
    }

	/**
	 * Search the YuGiOh prices API for the YuGiOh card requested.
	 * This only returns a single card and the spelling of the card
	 * must be perfect, including hythens (-) and spacing.
	 * Can return a monster or spell/trap card.
	 *
	 * @param name The YuGiOh card to search up, must match card name exactly.
	 */

    @Override
    public Call<YuGiOhCard> getCard(String name) {
        return service.getCard(name);
    }
}
