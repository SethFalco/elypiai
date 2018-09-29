package com.elypia.elypiai.poe;

import com.elypia.elypiai.utils.gson.deserializers.NestedDeserializer;
import com.google.gson.annotations.*;

public class Account {

	@SerializedName("name")
	private String name;

	@SerializedName("challenges")
	@JsonAdapter(NestedDeserializer.class)
	private int challenges;

	@SerializedName("guild")
	private Guild guild;

	@SerializedName("twitch")
	@JsonAdapter(NestedDeserializer.class)
	private String twitch;

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
