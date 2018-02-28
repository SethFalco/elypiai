package com.elypia.elypiai.runescape.events;

import com.elypia.elypiai.runescape.RuneScapeStat;
import com.elypia.elypiai.runescape.RuneScapeUser;
import com.elypia.elypiai.runescape.data.RSSkill;

public class MaxXpEvent {

	private static final String DESC_FORMAT = "%s just achieved 200 million XP in the %s skill with a final push of %,d!";

	private RuneScapeUser user;
	private RuneScapeStat stat;
	private RuneScapeStat previous;
	private RSSkill skill;
	private int xpGain;
	private String description;

	public MaxXpEvent(RuneScapeUser user, RuneScapeStat stat, RuneScapeStat previous) {
		this.user = user;
		this.stat = stat;
		this.skill = stat.getSkill();
		this.previous = previous;
		xpGain = stat.getXp() - previous.getXp();
		description = String.format(DESC_FORMAT, user.getUsername(), skill, stat.getLevel(), xpGain);
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

	public RSSkill getSkill() {
		return skill;
	}

	public int getGainedXp() {
		return xpGain;
	}

	public String getDescription() {
		return description;
	}
}
