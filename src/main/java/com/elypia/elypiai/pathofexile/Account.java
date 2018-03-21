package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

public class Account extends PoEObject {

	private String name;
	private int challenges;
	private Guild guild;
	private String twitch;

	public Account(PathOfExile poe, JSONObject object) {
		super(poe);

		name = object.getString("name");
		challenges = object.getJSONObject("challenges").getInt("total");

		if (object.has("guild"))
			guild = new Guild(poe, object.getJSONObject("guild"));

		if (object.has("twitch"))
			twitch = object.getJSONObject("twitch").getString("name");
	}

	public String getName() {
		return name;
	}

	public int getChallenges() {
		return challenges;
	}

	public Guild getGuild() {
		return guild;
	}

	public String getTwitch() {
		return twitch;
	}
}
