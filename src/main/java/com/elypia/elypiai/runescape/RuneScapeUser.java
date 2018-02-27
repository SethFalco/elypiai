package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RuneScapeSkill;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuneScapeUser {

	private static final String RANK_URL = "http://services.runescape.com/m=hiscore/compare";

	private String username;
	private int questsStarted;
	private int totalLevel;
	private int questsComplete;
	private int questsNotStarted;
	private long totalXp;
	private String rank;
	private int combatLevel;
	private boolean loggedIn;

	private List<String> activities;
	private Map<RuneScapeSkill, RuneScapeStat> stats;

	/**
	 * See {@link RuneScape#getPlayer(String)}
	 * @throws RestException
	 */

	public RuneScapeUser(JSONObject object) {
		activities = new ArrayList<>();
		stats = new HashMap<>();
		update(object);
	}

	public void update(JSONObject object) {
		if(object.has("error")) {
			if (object.getString("error").equals(RuneScape.PROFILE_PRIVATE))
				username = RuneScape.PROFILE_PRIVATE;

			return;
		}

		loggedIn		 	= !object.getString("loggedIn").equals("false");
		username			= object.getString("name");
		questsStarted	 	= object.getInt("questsstarted");
		questsComplete	 	= object.getInt("questscomplete");
		questsNotStarted	= object.getInt("questsnotstarted");
		totalLevel		 	= object.getInt("totalskill");
		totalXp				= object.getLong("totalxp");
		rank			 	= object.optString("rank", "Unranked");
		combatLevel		 	= object.getInt("combatlevel");

		JSONArray array = object.getJSONArray("skillvalues");
		for (int i = 0; i < array.length(); i++) {
			object = array.getJSONObject(i);
			RuneScapeStat stat = new RuneScapeStat(object);
			stats.put(stat.getSkill(), stat);
		}

		array = object.getJSONArray("activities");

		for (int i = 0; i < array.length(); i++)
			activities.add(array.getJSONObject(i).getString("details"));
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
	 * @return	The total number of completed quests.
	 */

	public int getQuestsComplete() {
		return questsComplete;
	}

	/**
	 * @return	The total number of quested not even started.
	 */

	public int getQuestsNotStarted() {
		return questsNotStarted;
	}

	/**
	 * @return	The total number of quests started but incomplete.
	 */

	public int getQuestsStarted() {
		return questsStarted;
	}

	/**
	 * @return	The total amount of XP the user has.
	 * 			(Max XP is: 5,400,000,000)
	 */

	public long getTotalXp() {
		return totalXp;
	}

	/**
	 * @return	Get the overall leaderboard ranking for
	 * 			this user.
	 */

	public String getRank() {
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

	public List<String> getActivities() {
		return activities;
	}

	/**
	 * @return	Get the leaderboard ranking url for this user.
	 */

	public String getLeaderboardUrl() {
		return RANK_URL + "?user1=" + username;
	}

	/**
	 * @return	Get the leaderboard ranking url for this
	 * 			user compared to the user provided.
	 */

	public String getLeaderboardUrl(RuneScapeUser user) {
		return getLeaderboardUrl() + "&user2=" + user.getUsername();
	}

	/**
	 * @return	Get the leaderboard ranking url for this user
	 * 			compared to the username provided.
	 */

	public String getLeaderboardUrl(String username) {
		return getLeaderboardUrl() + "&user2" + username;
	}

	/**
	 * @return	Returns the total xp formatted with commas
	 * 			as a String.
	 */

	public String getTotalXpPretty() {
		return String.format("%,d", totalXp);
	}

	/**
	 * @return	Returns the total level with commas formatted
	 * 			as a String.
	 */

	public String getTotalLevelAsString() {
		return String.format("%,d", totalLevel);
	}

	/**
	 * Returns a HashMap of Skills to Stats. Stats containing
	 * The players level, xp and rank in the skill.
	 *
	 * @return	Returns a Map of skills to stats.
	 */

	public Map<RuneScapeSkill, RuneScapeStat> getStats() {
		return stats;
	}

	public RuneScapeStat getStat(RuneScapeSkill skill) {
		return stats.get(skill);
	}
}