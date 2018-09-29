package com.elypia.elypiai.runescape.data;

import com.google.gson.annotations.SerializedName;

public enum Skill {

	/**
	 * This should only ever occur if a new skill is introduced
	 * and Elypiai hasn't updated to include it yet.
	 */
	UNKNOWN(-1, "Unknown", false),

	@SerializedName("0")
	ATTACK(0, "Attack", false),

	@SerializedName("1")
	DEFENCE(1, "Defence", false),

	@SerializedName("2")
	STRENGTH(2, "Strength", false),

	@SerializedName("3")
	CONSTITUTION(3, "Constitution", false),

	@SerializedName("4")
	RANGE(4, "Range", false),

	@SerializedName("5")
	PRAYER(5, "Prayer", false),

	@SerializedName("6")
	MAGIC(6, "Magic", false),

	@SerializedName("7")
	COOKING(7, "Cooking", false),

	@SerializedName("8")
	WOODCUTTING(8, "Woodcutting", false),

	@SerializedName("9")
	FLETCHING(9, "Fletching", false),

	@SerializedName("10")
	FISHING(10, "Fishing", false),

	@SerializedName("11")
	FIREMAKING(11, "Firemaking", false),

	@SerializedName("12")
	CRAFTING(12, "Crafting", false),

	@SerializedName("13")
	SMITHING(13, "Smithing", false),

	@SerializedName("14")
	MINING(14, "Mining", false),

	@SerializedName("15")
	HERBLORE(15, "Herblore", false),

	@SerializedName("16")
	AGILITY(16, "Agility", false),

	@SerializedName("17")
	THIEVING(17, "Theiving", false),

	@SerializedName("18")
	SLAYER(18, "Slayer", false),

	@SerializedName("19")
	FARMING(19, "Farming", false),

	@SerializedName("20")
	RUNECRAFTING(20, "Runecrafting", false),

	@SerializedName("21")
	HUNTER(21, "Hunter", false),

	@SerializedName("22")
	CONSTRUCTION(22, "Construction", false),

	@SerializedName("23")
	SUMMONING(23, "Summoning", false),

	@SerializedName("24")
	DUNGEONERING(24, "Dungeonering", false),

	@SerializedName("25")
	DIVINATION(25, "Divination", false),

	@SerializedName("26")
	INVENTION(26, "Invention", true);

	private final int ID;
	private final String NAME;
	private final boolean ELITE;

	Skill(int id, String name, boolean elite) {
		ID = id;
		NAME = name;
		ELITE = elite;
	}

	public int getId() {
		return ID;
	}

	public String getDisplay() {
		return NAME;
	}

	public boolean isElite() {
		return ELITE;
	}
}
