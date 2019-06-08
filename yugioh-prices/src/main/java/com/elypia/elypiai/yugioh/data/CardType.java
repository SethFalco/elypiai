package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

/**
 * The type of card this is, of the generic types,
 * monster, spell and trap. See {@link MagicType} or
 * {@link MonsterType} for more specific card details.
 */
public enum CardType {

	UNKNOWN("Unknown"),

	@SerializedName("monster")
	MONSTER("monster"),

	@SerializedName("spell")
	SPELL("spell"),

	@SerializedName("trap")
	TRAP("trap");

	private final String NAME;

	CardType(final String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public static CardType get(String name) {
		for (CardType type : values()) {
			if (type.NAME.equals(name))
				return type;
		}

		return UNKNOWN;
	}
}
