package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMode;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.function.Consumer;

public class OsuUser {

	private static final String BASE_PROFILE_URL = "https://osu.ppy.sh/u/";
	private static final String DEFAULT_AVATAR = "http://w.ppy.sh/c/c9/Logo.png";

	private int user_id;
	private OsuMode mode;
	private String username;
	private int count300;
	private int count100;
	private int count50;
	private int playcount;
	private long ranked_score;
	private long total_score;
	private int pp_rank;
	private double level;
	private double pp_raw;
	private double accuracy;
	private int count_rank_ss;
	private int count_rank_s;
	private int count_rank_a;
	private String country;
	private int pp_country_rank;
	private String profileUrl;

	/**
	 * See {@link Osu#getUser(String, OsuMode, Consumer, Consumer)}}
	 */

	public OsuUser(OsuMode mode, JSONObject object) {
		this.mode = mode;
		update(object);
		profileUrl = BASE_PROFILE_URL + user_id;
	}

	public void update(JSONObject object) {
		user_id         = object.getInt("user_id");
		username        = object.getString("username");
		count300        = object.optInt("count300", 0);
		count100        = object.optInt("count100", 0);
		count50         = object.optInt("count50", 0);
		playcount       = object.optInt("playcount", 0);
		ranked_score    = object.optLong("ranked_score", 0);
		total_score     = object.optLong("total_score", 0);
		pp_rank         = object.optInt("pp_rank", 0);
		level           = object.optDouble("level", 0);
		pp_raw          = object.optDouble("pp_raw", 0.);
		accuracy        = object.optDouble("accuracy", 0);
		count_rank_ss   = object.optInt("count_rank_ss", 0);
		count_rank_s    = object.optInt("count_rank_s", 0);
		count_rank_a    = object.optInt("count_rank_a", 0);
		country         = object.getString("country");
		pp_country_rank = object.getInt("pp_country_rank");
	}

	/**
	 * @return 	The id for the user. The id never changes.
	 */

	public int getUserID() {
		return user_id;
	}

	public OsuMode getGameMode() {
		return mode;
	}

	/**
	 * @return 	The username for the user.
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * @return 	The total number of 300s the user has ever hit.
	 */

	public int getCount300() {
		return count300;
	}

	/**
	 * @return 	The total number of 100s the user has ever hit.
	 */

	public int getCount100() {
		return count100;
	}

	/**
	 * @return 	The total number of 50s the user has ever hit.
	 */

	public int getCount50() {
		return count50;
	}

	/**
	 * @return 	The total number of plays, including unranked maps.
	 */

	public int getPlayCount() {
		return playcount;
	}

	/**
	 * @return 	The total score of the user, ranked maps only.
	 */

	public long getRankedScore() {
		return ranked_score;
	}

	/**
	 * @return 	The total score of the user, including unranked maps.
	 */

	public double getTotalScore() {
		return total_score;
	}

	/**
	 * @return 	Get the users current leaderboard ranking, global.
	 */

	public int getLeaderboardRank() {
		return pp_rank;
	}

	/**
	 * @return 	The users current level, unrounded.
	 */

	public double getLevel() {
		return level;
	}

	/**
	 * @return 	The total PP of the user, unrounded.
	 */

	public double getPP() {
		return pp_raw;
	}

	/**
	 * @return 	The accuracy of the user, unrounded.
	 */

	public double getAccuracy() {
		return accuracy;
	}

	/**
	 * @return 	Get total number of beatmaps with an SS rank.
	 */

	public int getCountRankSS() {
		return count_rank_ss;
	}

	/**
	 * @return 	Get total number of beatmaps with an S rank.
	 */

	public int getCountRankS() {
		return count_rank_s;
	}

	/**
	 * @return 	Get total number of beatmaps with an A rank.
	 */

	public int getCountRankA() {
		return count_rank_a;
	}

	/**
	 * @return 	Get the country the user is from as a country code.
	 */

	public String getCountry() {
		return country;
	}

	/**
	 * @return 	Get current leaderboard rank, country.
	 */

	public int getPPCountryRank() {
		return pp_country_rank;
	}

	/**
	 * @return 	Get the users osu! profile url.
	 */

	public String getProfileUrl() {
		return profileUrl;
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players accuracy.
	 */

	public String getAccuracyPretty() {
		return String.format("%05.2f%%", accuracy);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players total PP.
	 */

	public String getPpPretty() {
		return String.format("%,.2f", pp_raw);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players ranked score.
	 */

	public String getRankedScorePretty() {
		return String.format("%,d", ranked_score);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players total score including unranked.
	 */

	public String getTotalScorePretty() {
		return String.format("%,d", total_score);
	}

	/**
	 * @return	The profile picture of the osu player as a url.
	 */

	public String getAvatarUrl() {
		try {
			Document doc = Jsoup.parse(new URL(profileUrl), 3000);
			Elements elements = doc.getElementsByAttributeValue("alt", "User avatar");

			if (!elements.isEmpty())
				return "https:" + elements.attr("src");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return DEFAULT_AVATAR;
	}

	@Override
	public String toString() {
		return String.format("ID: %d%nUsername: %s%nPP: %s", user_id, username, getPpPretty());
	}
}
