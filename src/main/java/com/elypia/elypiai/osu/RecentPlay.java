package com.elypia.elypiai.osu;

import org.json.JSONObject;

public class RecentPlay {

	private boolean perfect;
	private String beatmap_id;
	private int score;
	private int maxcombo;
	private int count50;
	private int count100;
	private int count300;
	private int countmiss;
	private int countkatu;
	private int countgeki;
	private String enabled_mods;
	private String user_id;
	private String date;
	private String rank;

	RecentPlay(JSONObject object) {
		perfect 	 = object.getString("perfect").equals("1");
		score		 = object.optInt("score");
		maxcombo 	 = object.optInt("maxcombo");
		count50 	 = object.optInt("count50");
		count100     = object.optInt("count100");
		count300     = object.optInt("count300");
		countmiss    = object.optInt("countmiss");
		countkatu    = object.optInt("countkatu");
		countgeki    = object.optInt("countgeki");
		beatmap_id	 = object.getString("beatmap_id");
		enabled_mods = object.getString("enabled_mods");
		user_id		 = object.getString("user_id");
		date		 = object.getString("date");
		rank		 = object.getString("rank");
	}

	/**
	 * @return	The beatmap id of the recentplay.
	 */

	public String getBeatMapID() {
		return beatmap_id;
	}

	/**
	 * @return	The score the user got.
	 */

	public int getScore() {
		return score;
	}

	/**
	 * @return	The bigget combo the player received.
	 */

	public int getCombo() {
		return maxcombo;
	}

	/**
	 * @return	The total number of 50s the player hit.
	 */

	public int getCount50() {
		return count50;
	}

	/**
	 * @return	The total number of 100s the player hit.
	 */

	public int getCount100() {
		return count100;
	}

	/**
	 * @return	The total number of 300s the player hit.
	 */

	public int getCount300() {
		return count300;
	}

	/**
	 * @return	The total number of notes missed.
	 */

	public int getCountMiss() {
		return countmiss;
	}

	/**
	 * @return	The total number of katus in the play.
	 * 			(A set of notes/streams with 100s or above only.)
	 */

	public int getCountKatu() {
		return countkatu;
	}

	/**
	 * @return	The total number of gekis in the play.
	 * 			(A set of notes/stream with all 300s.)
	 */

	public int getCountGeki() {
		return countgeki;
	}

	/**
	 * @return	If the player achieved the maximum combo possible
	 * 			for the beatmap.
	 */

	public boolean getPerfect() {
		return perfect;
	}

	/**
	 * @return	The bitwise value of mods enabled.
	 */

	public String getMods() {
		return enabled_mods;
	}

	/**
	 * @return	The users id.
	 */

	public String getUserId() {
		return user_id;
	}

	/**
	 * Date and time the map was played in the format: <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was last updated.
	 */

	public String getDate() {
		return date;
	}

	public String getRank() {
		return rank;
	}
}
