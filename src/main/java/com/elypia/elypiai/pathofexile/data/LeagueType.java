package com.elypia.elypiai.pathofexile.data;

public enum LeagueType {

	/**
	 * Refers to all leagues.
	 */

	ALL("all"),

	/**
	 * Refers to only the main leagues,
	 * these are displayed in teh character screen.
	 */

	MAIN("main"),

	/**
	 * Refers to event leagues.
	 */

	EVENT("event"),

	/**
	 * Refers to league in a particular season.
	 */

	SEASON("season");

	private final String NAME;

	LeagueType(String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}
}
