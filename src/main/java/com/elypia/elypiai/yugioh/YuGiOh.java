package com.elypia.elypiai.yugioh;

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

	public YuGiOhCard getCard(String term) {
		YuGiOhCard card = new YuGiOhCard(term);
		return card.getName() != null ? card : null;
	}
}
