package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.PoEEndpoint;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

class PoERequester {

	private final PathOfExile POE;

	public PoERequester(PathOfExile poe) {
		this.POE = poe;
	}

	public void getStashTabs(Consumer<StashTabs> success, Consumer<IOException> failure) {
		String endpoint = PoEEndpoint.PUBLIC_STASH_TABS.getEndpoint();
		ElyRequest req = new ElyRequest(endpoint);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			POE.stashTabs = new StashTabs(POE, object);

			success.accept(POE.stashTabs);
		}, failure);
	}

	public void getLeagueRules(Consumer<Collection<LeagueRule>> success, Consumer<IOException> failure) {
		String endpoint = PoEEndpoint.LEAGUE_RULES.getEndpoint();
		ElyRequest req = new ElyRequest(endpoint);

		req.get(result -> {
			Collection<LeagueRule> rules = new ArrayList<>();
			JSONArray array = result.asJSONArray();

			for (int i = 0; i < array.length(); i++) {
				JSONObject ruleObject = array.getJSONObject(i);
				LeagueRule rule = new LeagueRule(POE, ruleObject);

				rules.add(rule);
			}

			success.accept(rules);
		}, failure);
	}

	public void getLeagueRule(String id, Consumer<LeagueRule> success, Consumer<IOException> failure) {
		String endpoint = PoEEndpoint.LEAGUE_RULES.getEndpoint();

		ElyRequest req = new ElyRequest(endpoint);
		req.addParam("id", id);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			LeagueRule rule = new LeagueRule(POE, object);

			success.accept(rule);
		}, failure);
	}
}
