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

package org.elypia.elypiai.yugioh;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.yugioh.data.Attribute;
import org.elypia.elypiai.yugioh.data.MonsterType;
import org.elypia.elypiai.yugioh.data.Race;

import java.util.Collection;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Monster extends TradingCard {

	@SerializedName("family")
	private Attribute attribute;

	@SerializedName("level")
	private int level;

	@SerializedName("type")
	private String typeLine;

	@SerializedName("atk")
	private int attack;

	@SerializedName("def")
	private int defense;

	/**
	 * This is not returned by the API but added to the
	 * object pre-deserialization.
	 */
	@SerializedName("race")
	private Race race;

	/**
	 * This is not returned by the API but added to the
	 * object pre-deserialization.
	 */
	@SerializedName("types")
	private List<MonsterType> types;

	/**
	 * @return	Get the attribute/attribute of the card.
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * @return	Get the level of the monster.
	 */
	public int getLevel() {
		return level;
	}

	public String getTypeLine() {
		return typeLine;
	}

	/**
	 * @return	Get the attack score of the monster.
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * @return	Det the defence score of the monster.
	 */
	public int getDefense() {
		return defense;
	}

	public Race getRace() {
		return race;
	}

	/**
	 * @return	Get the monsters type.
	 */
	public Collection<MonsterType> getTypes() {
		return types;
	}
}
