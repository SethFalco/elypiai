package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum AscendancyType {

	@SerializedName("Dualist")
	DUELIST("Dualist"),

	@SerializedName("Shadow")
	SHADOW("Shadow"),

	@SerializedName("Marauder")
	MARAUDER("Marauder"),

	@SerializedName("Witch")
	WITCH("Witch"),

	@SerializedName("Ranger")
	RANGER("Ranger"),

	@SerializedName("Templar")
	TEMPLAR("Templar"),

	@SerializedName("Scion")
	SCION("Scion");

	private final String NAME;

	AscendancyType(String name) {
		NAME = name;
	}

	public static AscendancyType get(String name) {
		for (AscendancyType ascendancy : values()) {
			if (ascendancy.NAME.equals(name))
				return ascendancy;
		}

		return null;
	}
}
