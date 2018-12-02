package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum MapGenre {

	@SerializedName("0")
	ANY(0),

	@SerializedName("1")
	UNSPECIFIED(1),

	@SerializedName("2")
	VIDEO_GAME(2),

	@SerializedName("3")
	ANIME(3),

	@SerializedName("4")
	ROCK(4),

	@SerializedName("5")
	POP(5),

	@SerializedName("6")
	OTHER(6),

	@SerializedName("7")
	NOVELTY(7),

	@SerializedName("9")
	HIP_HOP(9),

	@SerializedName("10")
	ELECTRONIC(10);

	private final int ID;

	MapGenre(int id) {
		ID = id;
	}

	public int getId() {
		return ID;
	}
}
