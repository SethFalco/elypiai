package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum GemColor {

	@SerializedName("R")
	RED("R"),

	@SerializedName("B")
	BLUE("B"),

	@SerializedName("G")
	GREEN("G"),

	@SerializedName("W")
	WHITE("W");

	private final String NAME;

	GemColor(String name) {
		NAME = name;
	}

	public static GemColor get(String apiName) {
		for (GemColor color : GemColor.values()) {
			if (color.NAME.equals(apiName))
				return color;
		}

		return null;
	}
}
