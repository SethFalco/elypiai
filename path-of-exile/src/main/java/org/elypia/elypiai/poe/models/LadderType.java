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

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum LadderType {

	UNKNOWN("Unknown"),

	@SerializedName("league")
	LEAGUE("league"),

	@SerializedName("pvp")
	PVP("pvp"),

	@SerializedName("labyrinth")
	LABYRINTH("labyrinth");

	private final String NAME;

	LadderType(String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public static LadderType get(String apiName) {
		for (LadderType type : LadderType.values()) {
			if (type.NAME.equals(apiName))
				return type;
		}

		return UNKNOWN;
	}
}
