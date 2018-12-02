package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemProperty {

	@SerializedName("name")
	private String name;

	@SerializedName("values")
	private List<List<String>> value;

	@SerializedName("displayMode")
	private int displayMode;

	@SerializedName("type")
	private int type;

	public String getName() {
		return name;
	}

	public List<List<String>> getValues() {
		return value;
	}

	public int getDisplayMode() {
		return displayMode;
	}

	public int getType() {
		return type;
	}
}
