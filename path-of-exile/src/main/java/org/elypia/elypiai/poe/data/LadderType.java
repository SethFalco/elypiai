/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Syed Shah)
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
