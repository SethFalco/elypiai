package com.elypia.elypiai.pathofexile;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Collection;
import java.util.function.Consumer;

public class PathOfExile {

	protected StashTabs stashTabs;

	private PoERequester requester;

	public PathOfExile() {
		requester = new PoERequester(this);
	}

	public void getStashTabs(Consumer<StashTabs> success, Consumer<UnirestException> failure) {
		requester.getStashTabs(success, failure);
	}

	public void getLeagueRules(Consumer<Collection<LeagueRule>> success, Consumer<UnirestException> failure) {
		requester.getLeagueRules(success, failure);
	}

	public void getLeagueRules(String id, Consumer<LeagueRule> success, Consumer<UnirestException> failure) {
		requester.getLeagueRules(id, success, failure);
	}

	/**
	 * Does NOT perform an API request. <br>
	 *
	 * @return The last cache of the {@link StashTabs}.
	 */

	public StashTabs getStashTabs() {
		return stashTabs;
	}
}
