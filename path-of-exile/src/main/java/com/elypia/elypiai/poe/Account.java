package com.elypia.elypiai.poe;

import com.elypia.elypiai.common.gson.deserializers.NestedDeserializer;
import com.elypia.elypiai.poe.data.Realm;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

public class Account {

	@SerializedName("name")
	private String name;

	@SerializedName("realm")
	private Realm realm;

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

	public Realm getRealm() {
		return realm;
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
