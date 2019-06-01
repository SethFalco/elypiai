package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

public class LeagueRule {

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
