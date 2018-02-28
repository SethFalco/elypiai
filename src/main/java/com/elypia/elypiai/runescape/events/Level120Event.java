package com.elypia.elypiai.runescape.events;

import com.elypia.elypiai.runescape.RuneScapeStat;
import com.elypia.elypiai.runescape.RuneScapeUser;
import com.elypia.elypiai.runescape.data.RSSkill;

public class Level120Event {

	private static final String DESC_FORMAT = "%s just achieved level 120 in %s with %,d XP!";

	private RuneScapeUser user;
	private RuneScapeStat stat;
	private RSSkill skill;
	private String description;


	public Level120Event(RuneScapeUser user, RuneScapeStat stat) {
		this.user = user;
		this.stat = stat;
		this.skill = stat.getSkill();
		description = String.format(DESC_FORMAT, user.getUsername(), skill, stat.getXp());
	}

	public RuneScapeUser getUser() {
		return user;
	}

	public RuneScapeStat getStat() {
		return stat;
	}

	public RSSkill getSkill() {
		return skill;
	}

	public String getDescription() {
		return description;
	}
}
