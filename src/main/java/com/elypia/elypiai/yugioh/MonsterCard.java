package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.*;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.annotations.*;

import java.util.*;
import java.util.regex.Pattern;

public class MonsterCard extends TradingCard {

	private static Pattern splitter = Pattern.compile("\\s*/\\s*");

	@SerializedName("type")
	private String type;

	@SerializedName("family")
	private MonsterFamily family;

	@SerializedName("atk")
	private int attack;

	@SerializedName("def")
	private int defense;

	@SerializedName("level")
	private int level;

	private MonsterAttribute attribute;

	private List<MonsterType> types;

	private MonsterCard() {

	}

	private void initTypes() {
		String[] split = splitter.split(type);

		if (split.length > 0)
			attribute = MonsterAttribute.get(split[0]);

		types = new ArrayList<>();

		if (split.length > 1) {
			for (int i = 1; i < split.length; i++)
				types.add(MonsterType.get(split[i]));
		}
	}

	public MonsterAttribute getAttribute() {
		if (attribute == null)
			initTypes();

		return attribute;
	}

	/**
	 * @return	Get the monsters type.
	 */

	public Collection<MonsterType> getMonsterTypes() {
		if (types == null)
			initTypes();

		return types;
	}

	/**
	 * @return	Get the family/attribute of the card.
	 */

	public MonsterFamily getFamily() {
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
