/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.poe.models;

/**
 * @author seth@elypia.org (Seth Falco)
 */
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
