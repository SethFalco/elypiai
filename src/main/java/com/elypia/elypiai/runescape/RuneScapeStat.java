package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RuneScapeSkill;
import org.json.JSONObject;

public class RuneScapeStat {

	private RuneScapeSkill skill;
	private int level;
	private int virtualLevel;
	private int xp;
	private int rank;

	RuneScapeStat(JSONObject object) {
		skill = RuneScapeSkill.getById(object.getInt("id"));
		level = object.getInt("level");
		xp = object.getInt("xp") / 10;
		rank = object.optInt("rank", -1);
		virtualLevel = RuneScape.calcLevelFromXp(xp);
	}

	/**
	 * @return	The RuneScapeSkill this object contains
	 * 			the stats on.
	 */

	public RuneScapeSkill getSkill() {
		return skill;
	}

	/**
	 * @return	The users current level in this skill.
	 */

	public int getLevel() {
		return level;
	}

	public int getVirtualLevel() {
		return virtualLevel;
	}

	/**
	 * @return	The users current xp in this skill.
	 */

	public int getXp() {
		return xp;
	}

	/**
	 * @return	The users current leaderboard rank
	 * 			for this skill.
	 */

	public int getRank() {
		return rank;
	}
}