package com.elypia.elypiai.runescape.data;

public enum RSSkill {

	ATTACK(0, "Attack", false),
	DEFENCE(1, "Defence", false),
	STRENGTH(2, "Strength", false),
	CONSTITUTION(0, "Constitution", false),
	RANGE(3, "Range", false),
	PRAYER(4, "Prayer", false),
	MAGIC(5, "Magic", false),
	COOKING(6, "Cooking", false),
	WOODCUTTING(7, "Woodcutting", false),
	FLETCHING(8, "Fletching", false),
	FISHING(9, "Fishing", false),
	FIREMAKING(10, "Firemaking", false),
	CRAFTING(11, "Crafting", false),
	SMITHING(12, "Smithing", false),
	MINING(13, "Mining", false),
	HERBLORE(14, "Herblore", false),
	AGILITY(15, "Agility", false),
	THIEVING(16, "Theiving", false),
	SLAYER(17, "Slayer", false),
	FARMING(18, "Farming", false),
	RUNECRAFTING(19, "Runecrafting", false),
	HUNTER(20, "Hunter", false),
	CONSTRUCTION(21, "Construction", false),
	SUMMONING(22, "Summoning", false),
	DUNGEONERING(23, "Dungeonering", false),
	DIVINATION(24, "Divination", false),
	INVENTION(25, "Invention", true);

	private final int id;
	private final String display;
	private final boolean elite;

	RSSkill(int id, String display, boolean elite) {
		this.id = id;
		this.display = display;
		this.elite = elite;
	}

	public int getId() {
		return id;
	}

	public String getDisplay() {
		return display;
	}

	public boolean isElite() {
		return elite;
	}

	public static RSSkill getById(int id) {
		for (RSSkill skill : values()) {
			if (skill.getId() == id)
				return skill;
		}

		return null;
	}
}
