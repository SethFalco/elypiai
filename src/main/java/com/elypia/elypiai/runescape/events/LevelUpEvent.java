package com.elypia.elypiai.runescape.events;

import com.elypia.elypiai.runescape.RuneScapeStat;
import com.elypia.elypiai.runescape.RuneScapeUser;
import com.elypia.elypiai.runescape.data.RuneScapeSkill;

public class LevelUpEvent {

	private static final String DESC_FORMAT = "Congratulations, %1$s just advanced a %2$s level.\n%1$s's %2$s level is now %3$s.";

	private RuneScapeUser user;
	private RuneScapeStat stat;
	private RuneScapeStat previous;
	private RuneScapeSkill skill;
	private String description;

	public LevelUpEvent(RuneScapeUser user, RuneScapeStat stat, RuneScapeStat previous) {
		this.user = user;
		this.stat = stat;
		this.previous = previous;
		this.skill = stat.getSkill();
		description = String.format(DESC_FORMAT, user.getUsername(), skill, stat.getLevel());
	}

	public RuneScapeUser getUser() {
		return user;
	}

	public RuneScapeStat getStat() {
		return stat;
	}

	public RuneScapeStat getStatBefore() {
		return previous;
	}

	public RuneScapeSkill getSkill() {
		return skill;
	}

	public String getDescription() {
		return description;
	}
}
