package com.elypia.elypiai.pathofexile.data;

public enum MatchStyle {

	SWISS("Swiss"),
	ARENA("Arena"),
	BLITZ("Blitz");

	private String apiName;

	MatchStyle(String apiName) {
		this.apiName = apiName;
	}

	public static MatchStyle getTypeByApiName(String apiName) {
		for (MatchStyle style : MatchStyle.values()) {
			if (style.apiName.equals(apiName))
				return style;
		}

		return null;
	}
}
