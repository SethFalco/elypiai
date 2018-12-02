package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class League extends CompactLeague {

	@SerializedName("description")
	private String description;

	@SerializedName("registerAt")
	private Date registerAt;

	@SerializedName("event")
	private boolean event;

	@SerializedName("rules")
	private List<LeagueRule> rules;

	public String getDescription() {
		return description;
	}

	public Date getRegisterAt() {
		return registerAt;
	}

	public boolean isEvent() {
		return event;
	}

	public List<LeagueRule> getRules() {
		return rules;
	}
}
