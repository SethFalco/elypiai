package com.elypia.elypiai.pathofexile.data;

public enum AscendancyType {

	DUELIST("Dualist"),
	SHADOW("Shadow"),
	MARAUDER("Marauder"),
	WITCH("Witch"),
	RANGER("Ranger"),
	TEMPLAR("Templar"),
	SCION("Scion");

	private String apiName;

	AscendancyType(String apiName) {
		this.apiName = apiName;
	}

	public static AscendancyType getByName(String apiName) {
		for (AscendancyType ascendancy : values()) {
			if (ascendancy.apiName.equals(apiName))
				return ascendancy;
		}

		return null;
	}
}
