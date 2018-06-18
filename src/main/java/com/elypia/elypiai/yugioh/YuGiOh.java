package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.function.Consumer;

public class YuGiOh {

	public static final String INFO = "http://yugiohprices.com/api/card_data/%s";
	public static final String IMAGE = "http://yugiohprices.com/api/card_image/%s";

	/**
	 * Search the YuGiOh prices API for the YuGiOh card requested.
	 * This only returns a single card and the spelling of the card
	 * must be perfect, including hythens (-) and spacing.
	 * Can return a monster or spell/trap card.
	 *
	 * @param 	term	The YuGiOh card to search up, must match card name exactly.
	 */

	public void getCard(String term, Consumer<YuGiOhCard> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(INFO, term);

		req.get(result -> {
			JsonObject object = result.asJsonObject();
			YuGiOhCard card = null;

			if (object.get("status").getAsString().equals("success")) {
				JsonObject data = object.getAsJsonObject("data");
				card = new YuGiOhCard(data);
			}

			success.accept(card);
		}, err -> {
			failure.accept(err);
		});
	}
}
