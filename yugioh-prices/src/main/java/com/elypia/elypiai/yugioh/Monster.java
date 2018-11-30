package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.*;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.annotations.SerializedName;

import java.util.*;
import java.util.regex.Pattern;

public class Monster extends TradingCard {

	/**
	 * The pattern used to split he cards types apart
	 * in order to store them as a {@link List<Race>}.
	 */
	private static final Pattern SPLITTER = Pattern.compile("\\s*/\\s*");

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
