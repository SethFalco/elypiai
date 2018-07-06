package com.elypia.elypiai.pathofexile.data;

import com.google.gson.annotations.SerializedName;

public enum LabyrinthDifficulty {

	@SerializedName("1")
	STANDARD(1),

	@SerializedName("2")
	CRUEL(2),

	@SerializedName("3")
	MERCILESS(3);

	private final int VALUE;

	LabyrinthDifficulty(int value) {
		VALUE = value;
	}

	public int getValue() {
		return VALUE;
	}
}
