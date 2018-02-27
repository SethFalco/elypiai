package com.elypia.elypiai.osu.data;

public enum OsuMode {

	OSU(0),
	TAIKO(1),
	CATCH_THE_BEAT(2),
	MANIA(3);

	private int id;

	OsuMode(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static OsuMode getById(int id) {
		for (OsuMode mode : values()) {
			if (mode.getId() == id)
				return mode;
		}

		return null;
	}
}

