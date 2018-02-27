package com.elypia.elypiai.osu.events;

import com.elypia.elypiai.osu.OsuUser;

public class PPUpEvent {

	private static final String DESC_FORMAT = "%s just gained %,.2f pp and went up %d ranks on the leaderboard!";

	private OsuUser user;
	private double earnedPP;
	private int rankChange;
	private String description;

	public PPUpEvent(OsuUser user, double previousPP, int rank) {
		this.user = user;
		earnedPP = user.getPP() - previousPP;
		rankChange = user.getLeaderboardRank() - rank;
		description = String.format(DESC_FORMAT, user.getUsername(), earnedPP, rankChange);
	}

	public OsuUser getUser() {
		return user;
	}

	public double getPPGained() {
		return earnedPP;
	}

	public int getRankChange() {
		return rankChange;
	}

	public String getPPGainedPretty() {
		return String.format("%,.2f", earnedPP);
	}

	public String getDescription() {
		return description;
	}
}
