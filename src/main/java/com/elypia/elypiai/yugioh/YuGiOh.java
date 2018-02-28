package com.elypia.elypiai.yugioh;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class YuGiOh {

	public static final String INFO_ENDPOINT = "http://yugiohprices.com/api/card_data/";
	public static final String IMAGE_ENDPOINT = "http://yugiohprices.com/api/card_image/";

	/**
	 * Search the YuGiOh prices API for the YuGiOh card requested.
	 * This only returns a single card and the spelling of the card
	 * must be perfect, including hythens (-) and spacing.
	 * Can return a monster or spell/trap card.
	 *
	 * @param 	term	The YuGiOh card to search up.
	 * @return			Card with all information on the card and
	 * 					a function to download the card image.
	 */

	public void getCard(String term) {
		Unirest.get(INFO_ENDPOINT + term).asJsonAsync(new Callback<JsonNode>() {
			@Override
			public void completed(HttpResponse<JsonNode> response) {
                JSONObject object = response.getBody().getObject();

				if(!object.toString().contains("No cards matching this name")) {
					JSONObject data = object.getJSONObject("data");

					YuGiOhCard card = new YuGiOhCard(data);
				}
			}

			@Override
			public void failed(UnirestException e) {

			}

			@Override
			public void cancelled() {

			}
		});
	}
}
