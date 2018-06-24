package com.elypia.elypiai.urbandictionary;

public enum UrbanResultType {

	/**
	 * This only occurs if there were no results.
	 */

	NO_RESULTS("no_results"),

	EXACT("exact");

	/**
	 * The name as it appears in the API.
	 */

	private final String apiName;

	UrbanResultType(String apiName) {
		this.apiName = apiName;
	}

	/**
	 * Get the relevent value based on the API name.
	 *
	 * @param apiName The name returned by the API for this field.
	 * @return The equivilent enum value.
	 */

	public static UrbanResultType getByName(String apiName) {
		for (UrbanResultType type : values()) {
			if (type.apiName.equalsIgnoreCase(apiName))
				return type;
		}

		return null;
	}
}
