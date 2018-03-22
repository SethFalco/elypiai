package com.elypia.elypiai.runescape;

import com.elypia.elypiai.runescape.data.RSSkill;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuneScapeUser {

	private static final String RANK_URL = "http://services.runescape.com/m=hiscore/compare";

	private RuneScape runescape;

	private String username;
	private int questsStarted;
	private int totalLevel;
	private int questsComplete;
	private int questsNotStarted;
	private long totalXp;
	private int rank;
	private int combatLevel;
	private boolean loggedIn;
	private boolean isPrivate;

	private List<Activity> activities;
	private Map<RSSkill, RuneScapeStat> stats;

	public RuneScapeUser(RuneScape runescape, JSONObject object) {
		this.runescape = runescape;
		activities = new ArrayList<>();
		stats = new HashMap<>();
		update(object);
	}

	public void update(JSONObject object) {
		if(object.has("error")) {
			String error = object.getString("error");

			if (error.equals("PROFILE_PRIVATE"))
				isPrivate = true;

			return;
		}

		loggedIn = object.optBoolean("loggedIn");
		username = object.getString("name");
		questsStarted = object.getInt("questsstarted");
		questsComplete = object.getInt("questscomplete");
		questsNotStarted = object.getInt("questsnotstarted");
		totalLevel = object.getInt("totalskill");
		totalXp	= object.getLong("totalxp");
		combatLevel	= object.getInt("combatlevel");

		String rankString = object.optString("rank", "-1");
		rankString = rankString.replace(",", "");
		rank = Integer.parseInt(rankString);

		JSONArray skillvalues = object.getJSONArray("skillvalues");
		for (int i = 0; i < skillvalues.length(); i++) {
			JSONObject skill = skillvalues.getJSONObject(i);
			RuneScapeStat stat = new RuneScapeStat(skill);
			stats.put(stat.getSkill(), stat);
		}

		JSONArray activitiesArray = object.getJSONArray("activities");
		for (int i = 0; i < activitiesArray.length(); i++) {
			JSONObject activityObj = activitiesArray.getJSONObject(i);
			Activity activity = new Activity(runescape, activityObj);
			activities.add(activity);
		}
	}

	public RuneScape getRunescape() {
		return runescape;
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
	 */

	public long getTotalXp() {
		return totalXp;
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

	public List<Activity> getActivities() {
		return activities;
	}

	/**
	 * @return	Get the leaderboard ranking url for this user.
	 */

	public String getLeaderboardUrl() {
		String encoded = username;

		try {
			encoded = URLEncoder.encode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return RANK_URL + "?user1=" + encoded;
	}

	/**
	 * @return	Get the leaderboard ranking url for this user compared to the user provided.
	 */

	public String getLeaderboardUrl(RuneScapeUser user) {
		return getLeaderboardUrl(user.getUsername());
	}

	/**
	 * @return	Get the leaderboard ranking url for this user
	 * 			compared to the username provided.
	 */

	public String getLeaderboardUrl(String username) {
		if (username.equalsIgnoreCase(this.username))
			return getLeaderboardUrl();

		String encoded = username;

		try {
			encoded = URLEncoder.encode(username, "UTF-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		return getLeaderboardUrl() + "&user2=" + encoded;
	}

	/**
	 * @return	Returns the total xp formatted with commas
	 * 			as a String.
	 */

	public String getTotalXpString() {
		return String.format("%,d", totalXp);
	}

	/**
	 * @return	Returns the total level with commas formatted
	 * 			as a String.
	 */

	public String getTotalLevelString() {
		return String.format("%,d", totalLevel);
	}

	/**
	 * Returns a HashMap of Skills to Stats. Stats containing
	 * The players level, xp and rank in the skill.
	 *
	 * @return	Returns a Map of skills to stats.
	 */

	public Map<RSSkill, RuneScapeStat> getStats() {
		return stats;
	}

	public RuneScapeStat getStat(RSSkill skill) {
		return stats.get(skill);
	}

	public boolean isPrivate() {
		return isPrivate;
	}
}
