package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.Skill;
import com.google.gson.annotations.SerializedName;

public class PlayerStat {

	@SerializedName("id")
	private Skill skill;

	@SerializedName("level")
	private int level;

	@SerializedName("xp")
	private int xp;

	@SerializedName("rank")
	private int rank;

	public int getVirtualLevel() {
		return RuneScape.parseXpAsLevel(getXp(), skill.isElite());
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
