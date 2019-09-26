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

package org.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

/**
 * The type of card this is, of the generic types,
 * monster, spell and trap. See {@link MagicType} or
 * {@link MonsterType} for more specific card details.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public enum CardType {

	UNKNOWN("Unknown"),

	@SerializedName("monster")
	MONSTER("monster"),

	@SerializedName("spell")
	SPELL("spell"),

	@SerializedName("trap")
	TRAP("trap");

	private final String NAME;

	CardType(final String name) {
		NAME = name;
	}

	public String getName() {
		return NAME;
	}

	public static CardType get(String name) {
		for (CardType type : values()) {
			if (type.NAME.equals(name))
				return type;
		}

		return UNKNOWN;
	}
}
