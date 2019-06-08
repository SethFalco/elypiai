package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class League extends CompactLeague {

	@SerializedName("description")
	private String description;

	@SerializedName("registerAt")
	private Date registerAt;

	@SerializedName("rules")
	private List<LeagueRule> rules;

	public String getDescription() {
		return description;
	}

	public Date getRegisterAt() {
		return registerAt;
	}

	public List<LeagueRule> getRules() {
		return rules;
	}
}
