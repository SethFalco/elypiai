package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum AscendancyClass {

	@SerializedName("Slayer")
	SLAYER(AscendancyType.DUELIST, "Slayer"),

	@SerializedName("Gladiator")
	GLADIATOR(AscendancyType.DUELIST, "Gladiator"),

	@SerializedName("Champion")
	CHAMPION(AscendancyType.DUELIST, "Champion"),

	@SerializedName("Assassin")
	ASSASSIN(AscendancyType.SHADOW, "Assassin"),

	@SerializedName("Saboteur")
	SABOTEUR(AscendancyType.SHADOW, "Saboteur"),

	@SerializedName("Trickster")
	TRICKSTER(AscendancyType.SHADOW, "Trickster"),

	@SerializedName("Juggernaut")
	JUGGERNAUT(AscendancyType.MARAUDER, "Juggernaut"),

	@SerializedName("Berserker")
	BERSERKER(AscendancyType.MARAUDER, "Berserker"),

	@SerializedName("Chieftain")
	CHIEFTAIN(AscendancyType.MARAUDER, "Chieftain"),

	@SerializedName("Necromancer")
	NERCROMANCER(AscendancyType.WITCH, "Nercromancer"),

	@SerializedName("Occultist")
	OCCULTIST(AscendancyType.WITCH, "Occultist"),

	@SerializedName("Elementalist")
	ELEMENTALIST(AscendancyType.WITCH, "Elementalist"),

	@SerializedName("Deadeye")
	DEADEYE(AscendancyType.RANGER, "Deadeye"),

	@SerializedName("Raider")
	RAIDER(AscendancyType.RANGER, "Raider"),

	@SerializedName("Pathfinder")
	PATHFINDER(AscendancyType.RANGER, "Pathfinder"),

	@SerializedName("Inquisitor")
	INQUISITOR(AscendancyType.TEMPLAR, "Inquistitor"),

	@SerializedName("Hierophant")
	HIEROPHANT(AscendancyType.TEMPLAR, "Hierophant"),

	@SerializedName("Guardian")
	GUARDIAN(AscendancyType.TEMPLAR, "Guardian"),

	@SerializedName("Ascendant")
	ASCENDANT(AscendancyType.SCION, "Ascendant");

	private final AscendancyType TYPE;
	private final String NAME;

	AscendancyClass(AscendancyType type, String name) {
		TYPE = type;
		NAME = name;
	}

	public AscendancyType getType() {
		return TYPE;
	}

	public static AscendancyClass get(String name) {
		for (AscendancyClass ascendancy : AscendancyClass.values()) {
			if (ascendancy.NAME.equals(name))
				return ascendancy;
		}

		return null;
	}
}
