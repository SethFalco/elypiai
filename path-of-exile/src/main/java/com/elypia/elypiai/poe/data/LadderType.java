package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum LadderType {

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

	public static LadderType get(String apiName) {
		for (LadderType type : LadderType.values()) {
			if (type.NAME.equals(apiName))
				return type;
		}

		return null;
	}
}
