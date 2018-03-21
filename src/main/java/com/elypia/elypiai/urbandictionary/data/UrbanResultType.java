package com.elypia.elypiai.urbandictionary.data;

public enum UrbanResultType {

	NO_RESULTS("no_results"),
	EXACT("exact");

	private final String apiName;

	UrbanResultType(String apiName) {
		this.apiName = apiName;
	}

	public static UrbanResultType getByName(String apiName) {
		for (UrbanResultType type : values()) {
			if (type.apiName.equalsIgnoreCase(apiName))
				return type;
		}

		return null;
	}
}
