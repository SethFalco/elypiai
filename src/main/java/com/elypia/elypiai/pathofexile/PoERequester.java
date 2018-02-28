package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.PoEEndpoint;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

class PoERequester {

	private PathOfExile poe;

	public PoERequester(PathOfExile poe) {
		this.poe = poe;
	}

	// Stash Tabs

	public void getStashTabs(Consumer<StashTabs> success, Consumer<UnirestException> failure) {
		String endpoint = PoEEndpoint.PUBLIC_STASH_TABS.getEndpoint();

		Unirest.get(endpoint).asJsonAsync(new Callback<JsonNode>( ) {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();

				StashTabs stashTabs = new StashTabs(poe, object);

				poe.stashTabs = stashTabs;
				success.accept(poe.stashTabs);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	// Rules

	public void getLeagueRules(Consumer<Collection<LeagueRule>> success, Consumer<UnirestException> failure) {
		String endpoint = PoEEndpoint.LEAGUE_RULES.getEndpoint();

		Unirest.get(endpoint).asJsonAsync(new Callback<JsonNode>( ) {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONArray array = response.getBody().getArray();

				Collection<LeagueRule> rules = new ArrayList<>();

				for (int i = 0; i < array.length(); i++) {
					JSONObject ruleObject = array.getJSONObject(i);
					LeagueRule rule = new LeagueRule(poe, ruleObject);

					rules.add(rule);
				}

				success.accept(rules);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	public void getLeagueRule(String id, Consumer<LeagueRule> success, Consumer<UnirestException> failure) {
		String endpoint = PoEEndpoint.LEAGUE_RULES.getEndpoint();

		HttpRequest request = Unirest.get(endpoint).queryString("id", id);
		request.asJsonAsync(new Callback<JsonNode>( ) {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				LeagueRule rule = new LeagueRule(poe, object);

				success.accept(rule);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}
}
