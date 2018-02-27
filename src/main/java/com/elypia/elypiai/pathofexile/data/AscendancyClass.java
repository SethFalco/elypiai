package com.elypia.elypiai.pathofexile.data;

public enum AscendancyClass {

	SLAYER(AscendancyType.DUELIST, "Slayer"),
	GLADIATOR(AscendancyType.DUELIST, "Gladiator"),
	CHAMPION(AscendancyType.DUELIST, "Champion"),
	ASSASSIN(AscendancyType.SHADOW, "Assassin"),
	SABOTEUR(AscendancyType.SHADOW, "Saboteur"),
	TRICKSTER(AscendancyType.SHADOW, "Trickster"),
	JUGGERNAUT(AscendancyType.MARAUDER, "Juggernaut"),
	BERSERKER(AscendancyType.MARAUDER, "Berserker"),
	CHIEFTAIN(AscendancyType.MARAUDER, "Chieftain"),
	NERCROMANCER(AscendancyType.WITCH, "Nercromancer"),
	OCCULTIST(AscendancyType.WITCH, "Occultist"),
	ELEMENTALIST(AscendancyType.WITCH, "Elementalist"),
	DEADEYE(AscendancyType.RANGER, "Deadeye"),
	RAIDER(AscendancyType.RANGER, "Raider"),
	PATHFINDER(AscendancyType.RANGER, "Pathfinder"),
	INQUISITOR(AscendancyType.TEMPLAR, "Inquistitor"),
	HIEROPHANT(AscendancyType.TEMPLAR, "Hierophant"),
	GUARDIAN(AscendancyType.TEMPLAR, "Guardian"),
	ASCENDANT(AscendancyType.SCION, "Ascendant");

	private AscendancyType type;
	private String apiName;

	AscendancyClass(AscendancyType type, String apiName) {
		this.type = type;
		this.apiName = apiName;
	}

	public AscendancyType getClassType() {
		return type;
	}

	public String getApiName() {
		return apiName;
	}

	@Override
	public String toString() {
		return apiName;
	}

	public static AscendancyClass getTypeByApiName(String apiName) {

		for (AscendancyClass ascendancy : AscendancyClass.values()) {
			if (ascendancy.getApiName().equals(apiName))
				return ascendancy;
		}

		return null;
	}
}
