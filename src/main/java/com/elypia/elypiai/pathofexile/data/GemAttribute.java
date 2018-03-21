package com.elypia.elypiai.pathofexile.data;

public enum GemAttribute {

	STRENGTH(GemColor.RED, "S"),
	DEXTERITY(GemColor.GREEN, "D"),
	INTELLIGANCE(GemColor.BLUE, "I");

	private GemColor color;
	private String apiName;

	GemAttribute(GemColor color, String apiName) {
		this.color = color;
		this.apiName = apiName;
	}

	public static GemAttribute getByName(String apiName) {

		for (GemAttribute attr : GemAttribute.values()) {
			if (attr.apiName.equals(apiName))
				return attr;
		}

		return null;
	}
}
