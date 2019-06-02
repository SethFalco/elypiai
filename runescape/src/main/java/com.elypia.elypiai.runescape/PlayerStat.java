package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.Skill;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

		return RuneScape.parseXpAsLevel(getXp());
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
