package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum MapLanguage {

	@SerializedName("0")
	ANY(0),

	@SerializedName("1")
	OTHER(1),

	@SerializedName("2")
	ENGLISH(2),

	@SerializedName("3")
	JAPANESE(3),

	@SerializedName("4")
	CHINESE(4),

	@SerializedName("5")
	INSTRUMENTAL(5),

	@SerializedName("6")
	KOREAN(6),

	@SerializedName("7")
	FRENCH(7),

	@SerializedName("8")
	GERMAN(8),

	@SerializedName("9")
	SWEDISH(9),

	@SerializedName("10")
	SPANISH(10),

	@SerializedName("11")
	ITALIAN(11);

	private final int ID;

	MapLanguage(int id) {
		ID = id;
	}

	public int getId() {
		return ID;
	}
}
