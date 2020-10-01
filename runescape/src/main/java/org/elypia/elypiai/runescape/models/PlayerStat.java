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

package org.elypia.elypiai.runescape.models;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.runescape.RuneScape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class PlayerStat {

	private static final Logger logger = LoggerFactory.getLogger(PlayerStat.class);

	@SerializedName("id")
	private Skill skill;

	@SerializedName("level")
	private int level;

	@SerializedName("xp")
	private int xp;

	@SerializedName("rank")
	private int rank;

	public int getVirtualLevel() {
		if (skill.isElite()) {
			logger.warn("Formula for elite skills is unknown, returning original level.");
			return level;
		}

		return RuneScape.getLevelFromXp(getXp());
	}

	/**
	 * @return	The RuneScapeSkill this object contains
	 * 			the stats on.
	 */

	public Skill getSkill() {
		return skill;
	}

	/**
	 * @return	The users current level in this skill.
	 */

	public int getLevel() {
		return level;
	}

	/**
	 * @return	The users current xp in this skill.
	 */

	public int getXp() {
		return xp / 10;
	}

	/**
	 * @return	The users current leaderboard rank
	 * 			for this skill.
	 */

	public int getRank() {
		return rank;
	}
}
