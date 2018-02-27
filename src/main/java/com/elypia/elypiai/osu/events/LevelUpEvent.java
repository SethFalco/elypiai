package com.elypia.elypiai.osu.events;

import com.elypia.elypiai.osu.OsuUser;

public class LevelUpEvent {

	private static final String DESC_FORMAT = "%s just went from level %,d to %,d in osu!";

	private OsuUser user;
	private int previousLevel;
	private String description;

	public LevelUpEvent(OsuUser user, int previousLevel) {
		this.user = user;
		this.previousLevel = previousLevel;
		description = String.format(DESC_FORMAT, user.getUsername(), previousLevel, user.getLevel());
	}

	public OsuUser getUser() {
		return user;
	}

	public int getPreviousLevel() {
		return previousLevel;
	}

	public String getDescription() {
		return description;
	}
}
