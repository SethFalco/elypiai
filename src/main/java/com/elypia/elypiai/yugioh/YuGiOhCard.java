package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.*;
import org.json.JSONObject;

public class YuGiOhCard {

	private String name;
	private String text;
	private YGOCardType cardType;

	protected YGOMonsterType type;
	protected YGOFamily family;
	protected int attack;
	protected int defense;
	protected int level;

	/**
	 * @param object The JSONObject from the request for parsing.
	 */

	public YuGiOhCard(JSONObject object) {
		name = object.getString("name");
		text = object.getString("text");
		cardType = YGOCardType.getByName(object.getString("card_type"));

		String typeString = object.optString("type", null);

		if (typeString != null) {
			String[] split = typeString.split("\\s*\\/\\s*");
			type = YGOMonsterType.valueOf(split[0].toUpperCase());

			family = YGOFamily.valueOf(object.getString("family").toUpperCase());
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

	public YGOCardType getCardType() {
		return cardType;
	}

	/**
	 * @return	Get the monsters type.
	 */

	public YGOMonsterType getTypes() {
		return type;
	}

	/**
	 * @return	Get the family/attribute of the card.
	 */

	public YGOFamily getFamily() {
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
