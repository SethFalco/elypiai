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

package org.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.yugioh.Monster;

/**
 * The family is the first attribute displayed on any
 * {@link Monster}
 *
 * @author seth@elypia.org (Seth Falco)
 */
public enum Attribute {

	UNKNOWN("Unknown"),

	@SerializedName("light")
	LIGHT("light"),

	@SerializedName("dark")
	DARK("dark"),

	@SerializedName("earth")
	EARTH("earth"),

	@SerializedName("wind")
	WIND("wind"),

	@SerializedName("fire")
	FIRE("fire"),

	@SerializedName("water")
	WATER("water"),

	@SerializedName("divine")
	DIVINE("divine");

	private final String NAME;

	Attribute(final String name) {
		NAME = name;
	}

	public static Attribute get(String name) {
		for (Attribute attribute : values()) {
			if (attribute.NAME.equals(name))
				return attribute;
		}

		return UNKNOWN;
	}
}
