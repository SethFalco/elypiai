package com.elypia.elypiai.pathofexile.data;

public enum PoEEndpoint {

	PUBLIC_STASH_TABS("http://api.pathofexile.com/public-stash-tabs"),
	LEAGUES("http://api.pathofexile.com/leagues"),
	LEAGUE_RULES("http://api.pathofexile.com/league-rules"),
	PVP_MATCHES("http://api.pathofexile.com/pvp-matches");

	private String endpoint;

	PoEEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getEndpoint() {
		return endpoint;
	}
}
