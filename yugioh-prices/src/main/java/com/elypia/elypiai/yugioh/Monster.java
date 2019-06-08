package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.Attribute;
import com.elypia.elypiai.yugioh.data.MonsterType;
import com.elypia.elypiai.yugioh.data.Race;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.List;

public class Monster extends TradingCard {

	@SerializedName("family")
	private Attribute attribute;

	@SerializedName("level")
	private int level;

	@SerializedName("type")
	private String typeLine;

	@SerializedName("atk")
	private int attack;

	@SerializedName("def")
	private int defense;

	/**
	 * This is not returned by the API but added to the
	 * object pre-deserialization.
	 */
	@SerializedName("race")
	private Race race;

	/**
	 * This is not returned by the API but added to the
	 * object pre-deserialization.
	 */
	@SerializedName("types")
	private List<MonsterType> types;

	/**
	 * @return	Get the attribute/attribute of the card.
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return	Get the level of the monster.
	 */
	public int getLevel() {
		return level;
	}

	public String getTypeLine() {
		return typeLine;
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

	public Race getRace() {
		return race;
	}

	/**
	 * @return	Get the monsters type.
	 */
	public Collection<MonsterType> getTypes() {
		return types;
	}
}
