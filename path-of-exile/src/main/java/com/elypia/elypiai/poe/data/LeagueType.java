package com.elypia.elypiai.poe.data;

public enum LeagueType {

	UNKNOWN("Unknown"),

	/**
	 * Refers to only the main leagues,
	 * these are displayed in teh character screen.
	 */
	MAIN("main"),

	/** Refers to event leagues. */
	EVENT("event"),

	/** Refers to league in a particular season. */
	SEASON("season");

	private final String NAME;

	LeagueType(String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}
}
