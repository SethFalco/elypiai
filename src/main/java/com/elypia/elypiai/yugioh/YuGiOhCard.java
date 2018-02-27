package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.data.MonsterAttribute;
import com.elypia.elypiai.yugioh.data.MonsterType;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class YuGiOhCard {

	private String name;
	private String text;
	private CardType cardType;

	protected MonsterType type;
	protected MonsterAttribute family;
	protected int attack;
	protected int defense;
	protected int level;

	/**
	 * See {@link YuGiOh#getCard(String)}
	 */

	protected YuGiOhCard(JSONObject object) {
		Unirest.get(INFO_LINK + cardName).asJsonAsync(new Callback<JsonNode>() {
			@Override
			public void completed(HttpResponse<JsonNode> response) {
				if(!resp.getContent().contains("No cards matching this name")) {
					JSONObject object = resp.getJSONObject().getJSONObject("data");
					name = object.getString("name");
					text = object.getString("text");
					cardType = YuGiOhCardType.valueOf(object.getString("card_type").toUpperCase());

					type = MonsterType.valueOf(object.getString("type").toUpperCase());

					// If does not have Type, assume is not a monster.
					if (type != null) {
						family = YuGiOhAttribute.valueOf(object.getString("family").toUpperCase());
						attack = object.getInt("atk");
						defense = object.getInt("def");
						level = object.getInt("level");
					}
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

	/**
	 * @return	The name of the monster.
	 */

	public String getName() {
		return name;
	}

	/**
	 * @return	The text on the card with the effect
	 * 			if the monster has one.
	 */

	public String getText() {
		return text;
	}

	/**
	 * @return	Return what kind of this card this is,
	 * 			can be a Spell, Trap, or Monster.
	 * 			If Monster, can be casted to a {@link MonsterCard}
	 * 			for access to additional fields.
	 */

	public CardType getCardType() {
		return cardType;
	}

	/**
	 * @return	Get the monsters type. See {@link YuGiOhMonsterType}
	 */

	public MonsterType getType() {
		return type;
	}

	/**
	 * @return	Get the family/attribute of the card.
	 * 			See {@link YuGiOhAttribute}
	 */

	public MonsterAttribute getFamily() {
		return family;
	}

	/**
	 * @return	Get the attack score of the monster.
	 */

	public int getAttack() {
		return attack;
	}

	/**
	 * @return	Det the defence score of the monster.
	 */

	public int getDefense() {
		return defense;
	}

	/**
	 * @return	Get the level of the monster.
	 */

	public int getLevel() {
		return level;
	}
}
