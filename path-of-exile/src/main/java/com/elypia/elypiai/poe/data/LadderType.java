package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum LadderType {

	UNKNOWN("Unknown"),

	@SerializedName("league")
	LEAGUE("league"),

	@SerializedName("pvp")
	PVP("pvp"),

	@SerializedName("labyrinth")
	LABYRINTH("labyrinth");

	private final String NAME;

	LadderType(String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public static LadderType get(String apiName) {
		for (LadderType type : LadderType.values()) {
			if (type.NAME.equals(apiName))
				return type;
		}

		return UNKNOWN;
	}
}
