/*
 * Copyright 2019-2020 Elypia CIC
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
 * @author seth@elypia.org (Seth Falco)
 */
public enum GemAttribute {

	UNKNOWN(GemColor.UNKNOWN, null),

	@SerializedName("S")
	STRENGTH(GemColor.RED, "S"),

	@SerializedName("D")
	DEXTERITY(GemColor.GREEN, "D"),

	@SerializedName("I")
	INTELLIGANCE(GemColor.BLUE, "I");

	private final GemColor COLOR;
	private final String NAME;

	GemAttribute(GemColor color, String name) {
		COLOR = color;
		NAME = name;
	}

	public GemColor getColor() {
		return COLOR;
	}

	public String getName() {
		return NAME;
	}

	public static GemAttribute get(String apiName) {
		for (GemAttribute attr : GemAttribute.values()) {
			if (attr.NAME.equals(apiName))
				return attr;
		}

		return UNKNOWN;
	}
}
