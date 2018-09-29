package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum MapStatus {

	@SerializedName("-2")
	GRAVEYARD(-2),

	@SerializedName("-1")
	WIP(-1),

	@SerializedName("0")
	PENDING(0),

	@SerializedName("1")
	RANKED(1),

	@SerializedName("2")
	APPROVED(2),

	@SerializedName("3")
	QUALIFIED(3),

	@SerializedName("4")
	LOVED(4);

	private final int ID;

	MapStatus(int id) {
		ID = id;
	}

	public int getId() {
		return ID;
	}
}