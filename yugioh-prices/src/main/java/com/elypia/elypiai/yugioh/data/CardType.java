package com.elypia.elypiai.yugioh.data;

import com.elypia.elypiai.restutils.IEnum;
import com.google.gson.annotations.SerializedName;

public enum CardType implements IEnum<CardType> {

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

	@Override
	public String getName() {
		return NAME;
	}
}
