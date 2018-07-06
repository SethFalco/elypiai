package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

public enum CardType {

	@SerializedName("monster")
	MONSTER,

	@SerializedName("spell")
	SPELL,

	@SerializedName("trap")
	TRAP
}
