package com.elypia.elypiai.yugioh.data;

public enum YGOCardType {

	MONSTER("monster"),
	SPELL("spell"),
	TRAP("trap");

	private String apiName;

	YGOCardType(String apiName) {
		this.apiName = apiName;
	}

	public static YGOCardType getByName(String apiName) {
		for (YGOCardType type : values()) {
			if (type.apiName.equalsIgnoreCase(apiName))
				return type;
		}

		return null;
	}
}
