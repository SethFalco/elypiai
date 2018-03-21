package com.elypia.elypiai.pathofexile.data;

public enum GemColor {

	RED("R"),
	BLUE("B"),
	GREEN("G"),
	WHITE("W");

	private String apiName;

	GemColor(String apiName) {
		this.apiName = apiName;
	}

	public static GemColor getByName(String apiName) {

		for (GemColor color : GemColor.values()) {
			if (color.apiName.equals(apiName))
				return color;
		}

		return null;
	}
}
