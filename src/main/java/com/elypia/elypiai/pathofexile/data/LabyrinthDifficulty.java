package com.elypia.elypiai.pathofexile.data;

public enum LabyrinthDifficulty {

	STANDARD(1),
	CRUEL(2),
	MERCILESS(3);

	private int value;

	LabyrinthDifficulty(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
