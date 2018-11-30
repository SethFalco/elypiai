package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum MatchStyle {

	@SerializedName("Swiss")
	SWISS("Swiss"),

	@SerializedName("Arena")
	ARENA("Arena"),

	@SerializedName("Blitz")
	BLITZ("Blitz");

	private final String NAME;

	MatchStyle(String name) {
		NAME = name;
	}

	public static MatchStyle get(String name) {
		for (MatchStyle style : MatchStyle.values()) {
			if (style.NAME.equals(name))
				return style;
		}

		return null;
	}
}
