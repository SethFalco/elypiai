package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.*;
import com.elypia.elypiai.runescape.deserializers.CommaIntergerDeserializer;
import com.google.gson.annotations.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Player {

	private static final String RANK_URL = "http://services.runescape.com/m=hiscore/compare";

	@SerializedName("name")
	private String username;

	@SerializedName("totalskill")
	private int totalLevel;

	@SerializedName("totalxp")
	private long totalXp;

	@SerializedName("questscomplete")
	private int questsComplete;

	@SerializedName("questsstarted")
	private int questsStarted;

	@SerializedName("questsnotstarted")
	private int questsNotStarted;

	@SerializedName("rank")
	@JsonAdapter(CommaIntergerDeserializer.class)
	private int rank;

	@SerializedName("combatlevel")
	private int combatLevel;

	@SerializedName("loggedIn")
	private boolean loggedIn;

	@SerializedName("activities")
	private List<Activity> activities;

	@SerializedName("skillvalues")
	private Collection<PlayerStat> stats;

	@SerializedName("error")
	private RuneScapeError error;

	/**
	 * @return	Get the leaderboard ranking url for this user.
	 */
	public String getLeaderboardUrl() {
		String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
		return RANK_URL + "?user1=" + encoded;
	}

	/**
	 * @return	Get the leaderboard ranking url for this user
	 * 			compared to the username provided.
	 */
	public String getLeaderboardUrl(String username) {
		if (username.equalsIgnoreCase(this.username))
			return getLeaderboardUrl();

		String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);

		return getLeaderboardUrl() + "&user2=" + encoded;
	}

	/**
	 * @return	Returns the name of the user, or "PROFILE_PRIVATE"
	 * 			if the users account is set to private.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return	The total level of the user.
	 * 			(Every single skills level added together.
	 */
	public int getTotalLevel() {
		return totalLevel;
	}

	/**
	 * @return	Returns the total level with commas formatted
	 * 			as a String.
	 */
	public String getTotalLevelString() {
		return String.format("%,d", totalLevel);
	}

	/**
	 * @return	The total amount of XP the user has.
	 */
	public long getTotalXp() {
		return totalXp;
	}

	/**
	 * @return	Returns the total xp formatted with commas
	 * 			as a String.
	 */
	public String getTotalXpString() {
		return String.format("%,d", totalXp);
	}

	/**
	 * @return	The total number of completed quests.
	 */
	public int getQuestsComplete() {
		return questsComplete;
	}

	/**
	 * @return	The total number of quests started but incomplete.
	 */
	public int getQuestsStarted() {
		return questsStarted;
	}

	/**
	 * @return	The total number of quested not even started.
	 */
	public int getQuestsNotStarted() {
		return questsNotStarted;
	}

	/**
	 * @return	Get the overall leaderboard ranking for this user.
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @return	Get the combat level of the player.
	 */
	public int getCombatLevel() {
		return combatLevel;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public RuneScapeError getError() {
		return error;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	/**
	 * Returns a HashMap of Skills to Stats. Stats containing
	 * The players level, xp and rank in the skill.
	 *
	 * @return	Returns a Map of skills to stats.
	 */
	public Collection<PlayerStat> getStats() {
		return Collections.unmodifiableCollection(stats);
	}

	public PlayerStat getStat(Skill skill) {
		for (PlayerStat stat : stats) {
			if (stat.getSkill() == skill)
				return stat;
		}

		return null;
	}
}
