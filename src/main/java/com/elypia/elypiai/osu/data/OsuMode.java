package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum OsuMode {

	@SerializedName("0")
	OSU(0),

	@SerializedName("1")
	TAIKO(1),

	@SerializedName("2")
	CATCH_THE_BEAT(2),

	@SerializedName("3")
	MANIA(3);

	private final int ID;

	OsuMode(int id) {
		ID = id;
	}

	public int getId() {
		return ID;
	}
}

