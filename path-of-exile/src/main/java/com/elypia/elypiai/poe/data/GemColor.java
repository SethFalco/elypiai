package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

import java.awt.*;

public enum GemColor {

	@SerializedName("R")
	RED("R", Color.RED),

	@SerializedName("B")
	BLUE("B", Color.BLUE),

	@SerializedName("G")
	GREEN("G", Color.GREEN),

	@SerializedName("W")
	WHITE("W", Color.WHITE);

	private final String NAME;
	private final Color COLOR;

	GemColor(String name, Color color) {
		NAME = name;
		COLOR = color;
	}

	public String getName() {
		return NAME;
	}

	public Color getColor() {
		return COLOR;
	}

	public static GemColor get(String name) {
		for (GemColor color : GemColor.values()) {
			if (color.NAME.equals(name))
				return color;
		}

		return null;
	}
}
