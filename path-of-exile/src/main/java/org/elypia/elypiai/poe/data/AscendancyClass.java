/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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
public enum AscendancyClass {

	UNKNOWN(AscendancyType.UNKNOWN, "Unknown"),

	@SerializedName("Slayer")
	SLAYER(AscendancyType.DUELIST, "Slayer"),

	@SerializedName("Gladiator")
	GLADIATOR(AscendancyType.DUELIST, "Gladiator"),

	@SerializedName("Champion")
	CHAMPION(AscendancyType.DUELIST, "Champion"),

	@SerializedName("Assassin")
	ASSASSIN(AscendancyType.SHADOW, "Assassin"),

	@SerializedName("Saboteur")
	SABOTEUR(AscendancyType.SHADOW, "Saboteur"),

	@SerializedName("Trickster")
	TRICKSTER(AscendancyType.SHADOW, "Trickster"),

	@SerializedName("Juggernaut")
	JUGGERNAUT(AscendancyType.MARAUDER, "Juggernaut"),

	@SerializedName("Berserker")
	BERSERKER(AscendancyType.MARAUDER, "Berserker"),

	@SerializedName("Chieftain")
	CHIEFTAIN(AscendancyType.MARAUDER, "Chieftain"),

	@SerializedName("Necromancer")
	NERCROMANCER(AscendancyType.WITCH, "Nercromancer"),

	@SerializedName("Occultist")
	OCCULTIST(AscendancyType.WITCH, "Occultist"),

	@SerializedName("Elementalist")
	ELEMENTALIST(AscendancyType.WITCH, "Elementalist"),

	@SerializedName("Deadeye")
	DEADEYE(AscendancyType.RANGER, "Deadeye"),

	@SerializedName("Raider")
	RAIDER(AscendancyType.RANGER, "Raider"),

	@SerializedName("Pathfinder")
	PATHFINDER(AscendancyType.RANGER, "Pathfinder"),

	@SerializedName("Inquisitor")
	INQUISITOR(AscendancyType.TEMPLAR, "Inquistitor"),

	@SerializedName("Hierophant")
	HIEROPHANT(AscendancyType.TEMPLAR, "Hierophant"),

	@SerializedName("Guardian")
	GUARDIAN(AscendancyType.TEMPLAR, "Guardian"),

	@SerializedName("Ascendant")
	ASCENDANT(AscendancyType.SCION, "Ascendant");

	private final AscendancyType TYPE;
	private final String NAME;

	AscendancyClass(AscendancyType type, String name) {
		TYPE = type;
		NAME = name;
	}

	public AscendancyType getType() {
		return TYPE;
	}

	public static AscendancyClass get(String name) {
		for (AscendancyClass ascendancy : AscendancyClass.values()) {
			if (ascendancy.NAME.equals(name))
				return ascendancy;
		}

		return UNKNOWN;
	}
}
