package com.elypia.elypiai.runescape.data;

public enum RSSkill {

	ATTACK(0, "Attack", false),
	DEFENCE(1, "Defence", false),
	STRENGTH(2, "Strength", false),
	CONSTITUTION(3, "Constitution", false),
	RANGE(4, "Range", false),
	PRAYER(5, "Prayer", false),
	MAGIC(6, "Magic", false),
	COOKING(7, "Cooking", false),
	WOODCUTTING(8, "Woodcutting", false),
	FLETCHING(9, "Fletching", false),
	FISHING(10, "Fishing", false),
	FIREMAKING(11, "Firemaking", false),
	CRAFTING(12, "Crafting", false),
	SMITHING(13, "Smithing", false),
	MINING(14, "Mining", false),
	HERBLORE(15, "Herblore", false),
	AGILITY(16, "Agility", false),
	THIEVING(17, "Theiving", false),
	SLAYER(18, "Slayer", false),
	FARMING(19, "Farming", false),
	RUNECRAFTING(20, "Runecrafting", false),
	HUNTER(21, "Hunter", false),
	CONSTRUCTION(22, "Construction", false),
	SUMMONING(23, "Summoning", false),
	DUNGEONERING(24, "Dungeonering", false),
	DIVINATION(25, "Divination", false),
	INVENTION(26, "Invention", true);

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
