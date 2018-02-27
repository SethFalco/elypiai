package com.elypia.elypiai.pathofexile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;

public class League extends CompactLeague {

	private String description;
	private Instant registerAt;
	private boolean event;
	private Collection<LeagueRule> rules;

	public League(PathOfExile poe, JSONObject object) {
		super(poe, object);
		rules = new ArrayList<>();

		description = object.getString("description");
		event = object.getBoolean("event");

		String registerDate = object.optString("registerAt", null);

		if (registerDate != null)
			registerAt = Instant.parse(registerDate);

		JSONArray rulesArray = object.getJSONArray("rules");

		for (int i = 0; i < rulesArray.length(); i++) {
			JSONObject ruleObject = rulesArray.getJSONObject(i);
			LeagueRule rule = new LeagueRule(poe, ruleObject);

			rules.add(rule);
		}
	}

	public String getDescription() {
		return description;
	}

	public Instant getRegisterAt() {
		return registerAt;
	}

	public boolean isEvent() {
		return event;
	}

	public Collection<LeagueRule> getRules() {
		return rules;
	}
}
