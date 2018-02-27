package com.elypia.elypiai.osu.data;

public enum MapLanguage {

	ANY(0),
	OTHER(1),
	ENGLISH(2),
	JAPANESE(3),
	CHINESE(4),
	INSTRUMENTAL(5),
	KOREAN(6),
	FRENCH(7),
	GERMAN(8),
	SWEDISH(9),
	SPANISH(10),
	ITALIAN(11);

	private int id;

	MapLanguage(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static MapLanguage getById(int id) {
		for (MapLanguage language : values()) {
			if (language.id == id)
				return language;
		}

		return null;
	}
}
