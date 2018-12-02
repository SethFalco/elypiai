package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum StashType {

	@SerializedName("PremiumStash")
	PREMIUM_STASH("PremiumStash");

	private final String NAME;

	StashType(String name) {
		NAME = name;
	}

	public static StashType get(String name) {
		for (StashType type : StashType.values()) {
			if (type.NAME.equals(name))
				return type;
		}

		return null;
	}
}
