package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.data.MonsterAttribute;
import com.elypia.elypiai.yugioh.data.MonsterType;
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
	 * @param object The JSONObject from the request for parsing.
	 */

	protected YuGiOhCard(JSONObject object) {
		name = object.getString("name");
		text = object.getString("text");
		cardType = CardType.valueOf(object.getString("card_type").toUpperCase());

		type = MonsterType.valueOf(object.getString("type").toUpperCase());

		// If does not have Type, assume is not a monster.
		if (type != null) {
			family = MonsterAttribute.valueOf(object.getString("family").toUpperCase());
			attack = object.getInt("atk");
			defense = object.getInt("def");
			level = object.getInt("level");
		}
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
	 */

	public CardType getCardType() {
		return cardType;
	}

	/**
	 * @return	Get the monsters type.
	 */

	public MonsterType getType() {
		return type;
	}

	/**
	 * @return	Get the family/attribute of the card.
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
