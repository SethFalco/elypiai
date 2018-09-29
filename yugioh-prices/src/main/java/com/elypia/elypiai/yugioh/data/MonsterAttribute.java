package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

public enum MonsterAttribute {

	@SerializedName("Dragon")
	DRAGON("Dragon"),

	@SerializedName("Spellcaster")
	SPELLCASTER("Spellcaster"),

	@SerializedName("Zombie")
	ZOMBIE("Zombie"),

	@SerializedName("Warrior")
	WARRIOR("Warrior"),

	@SerializedName("Beast-Warrior")
	BEAST_WARRIOR("Beast-Warrior"),

	@SerializedName("Beast")
	BEAST("Beast"),

	@SerializedName("Winged Beast")
	WINGED_BEAST("Winged Beast"),

	@SerializedName("Fiend")
	FIEND("Fiend"),

	@SerializedName("Fairy")
	FAIRY("Fairy"),

	@SerializedName("Insect")
	INSECT("Insect"),

	@SerializedName("Dinosaur")
	DINOSAUR("Dinosaur"),

	@SerializedName("Reptile")
	REPTILE("Reptile"),

	@SerializedName("Fish")
	FISH("Fish"),

	@SerializedName("Sea Serpent")
	SEA_SERPENT("Sea Serpent"),

	@SerializedName("Machine")
	MACHINE("Machine"),

	@SerializedName("Thunder")
	THUNDER("Thunder"),

	@SerializedName("Aqua")
	AQUA("Aqua"),

	@SerializedName("Pyro")
	PYRO("Pyro"),

	@SerializedName("Rock")
	ROCK("Rock"),

	@SerializedName("Plant")
	PLANT("Plant"),

	@SerializedName("Psychic")
	PSYCHIC("Psychic"),

	@SerializedName("Divine-Beast")
	DIVINE_BEAST("Divine-Beast"),

	@SerializedName("WYRM")
	WYRM("WYRM");

	private final String NAME;

	MonsterAttribute(final String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public static MonsterAttribute get(String name) {
		for (MonsterAttribute attribute : values()) {
			if (attribute.NAME.equals(name))
				return attribute;
		}

		return null;
	}
}
