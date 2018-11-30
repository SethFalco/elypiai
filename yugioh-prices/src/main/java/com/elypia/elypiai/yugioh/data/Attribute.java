package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

/**
 * The family is the first attribute displayed on any
 * {@link com.elypia.elypiai.yugioh.Monster}
 */
public enum Attribute {

	@SerializedName("light")
	LIGHT("light"),

	@SerializedName("dark")
	DARK("dark"),

	@SerializedName("earth")
	EARTH("earth"),

	@SerializedName("wind")
	WIND("wind"),

	@SerializedName("fire")
	FIRE("fire"),

	@SerializedName("water")
	WATER("water"),

	@SerializedName("divine")
	DIVINE("divine");

	private final String NAME;

	Attribute(final String name) {
		NAME = name;
	}

	public static Attribute get(String name) {
		for (Attribute attribute : values()) {
			if (attribute.NAME.equals(name))
				return attribute;
		}

		return null;
	}
}
