package com.elypia.elypiai.pathofexile;

import java.io.IOException;
import java.util.Collection;
import java.util.function.Consumer;

public class PathOfExile {

	protected StashTabs stashTabs;

	private PoERequester requester;

	public PathOfExile() {
		requester = new PoERequester(this);
	}

	public void getStashTabs(Consumer<StashTabs> success, Consumer<IOException> failure) {
		requester.getStashTabs(success, failure);
	}

	public void getLeagueRules(Consumer<Collection<LeagueRule>> success, Consumer<IOException> failure) {
		requester.getLeagueRules(success, failure);
	}

	public void getLeagueRule(String id, Consumer<LeagueRule> success, Consumer<IOException> failure) {
		requester.getLeagueRule(id, success, failure);
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
