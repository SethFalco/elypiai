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

	public String getApiName() {
		return apiName;
	}

	@Override
	public String toString() {
		return apiName;
	}

	public static GemColor getTypeByApiName(String apiName) {

		for (GemColor color : GemColor.values()) {
			if (color.getApiName().equals(apiName))
				return color;
		}

		return null;
	}
}
