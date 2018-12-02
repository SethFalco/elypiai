package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum GemAttribute {

	@SerializedName("S")
	STRENGTH(GemColor.RED, "S"),

	@SerializedName("D")
	DEXTERITY(GemColor.GREEN, "D"),

	@SerializedName("I")
	INTELLIGANCE(GemColor.BLUE, "I");

	private final GemColor COLOR;
	private final String NAME;

	GemAttribute(GemColor color, String name) {
		COLOR = color;
		NAME = name;
	}

	public GemColor getColor() {
		return COLOR;
	}

	public String getName() {
		return NAME;
	}

	public static GemAttribute get(String apiName) {
		for (GemAttribute attr : GemAttribute.values()) {
			if (attr.NAME.equals(apiName))
				return attr;
		}

		return null;
	}
}
