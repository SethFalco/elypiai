package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

public class LeagueRule extends PoEObject {

	private int id;
	private String name;
	private String description;

	public LeagueRule(PathOfExile poe, JSONObject object) {
		super(poe);

		id = object.getInt("id");
		name = object.getString("name");
		description = object.getString("description");
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
