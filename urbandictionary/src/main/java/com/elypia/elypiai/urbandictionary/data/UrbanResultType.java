package com.elypia.elypiai.urbandictionary.data;

import com.google.gson.annotations.SerializedName;

public enum UrbanResultType {

	/**
	 * This only occurs if there were no results.
	 */
	@SerializedName("no_results")
	NO_RESULTS,

	@SerializedName("exact")
	EXACT
}
