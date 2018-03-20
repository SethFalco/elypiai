package com.elypia.elypiai.pathofexile.data;

public enum StashType {

	PREMIUM_STASH("PremiumStash");

	private String apiName;

	StashType(String apiName) {
		this.apiName = apiName;
	}

	public static StashType getTypeByApiName(String apiName) {

		for (StashType type : StashType.values()) {
			if (type.apiName.equals(apiName))
				return type;
		}

		return null;
	}
}
