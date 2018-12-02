package com.elypia.elypiai.steam.data;

import com.google.gson.annotations.SerializedName;

public enum PersonaState {

	@SerializedName("0")
	OFFLINE,

	@SerializedName("1")
	ONLINE,

	@SerializedName("2")
	BUSY,

	@SerializedName("3")
	AWAY,

	@SerializedName("4")
	SNOOZE,

	@SerializedName("5")
	LOOKING_TO_TRADE,

	@SerializedName("6")
	LOOKING_TO_PLAY
}
