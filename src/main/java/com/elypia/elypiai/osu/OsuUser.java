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

	/**
	 * The identifier for the user, this will never change.
	 */

	private int userId;

	/**
	 * The {@link OsuMode} this user is for.
	 * A user can have different stats per mode, this is the
	 * mode this instance of the user is representing.
	 */

	private OsuMode mode;

	/**
	 * The username of the player, this can be changed so is not
	 * good for storing identity of the player and should just be
	 * for display purposes. See {@link #userId} for an identifier.
	 */

	private String username;

	/**
	 * The number of times the player has clicked and scored a 300.
	 */

	private int count300;

	/**
	 * The number of times the player has clicked and scored a 100.
	 */

	private int count100;

	/**
	 * The number of times the player has clicked and scored a 50.
	 */

	private int count50;

	/**
	 * The total number of songs the user has played altogether.
	 */

	private int playcount;

	/**
	 * The total score the player has obtained from ranked maps only.
	 */

	private long rankedScore;

	/**
	 * The total score the player has obtained on any map.
	 */

	private long totalScore;

	/**
	 * The leaderboard ranking of the player determined by {@link #pp PP}.
	 */

	private int rank;

	/**
	 * The level of the player.
	 */

	private double level;

	/**
	 * The total PP of the player, obtained from playing ranked maps.
	 */

	private double pp;

	/**
	 * The average accuracy of the player, formatted as: 92.789.
	 */

	private double accuracy;

	/**
	 * The total number of times the player has obtained a rank of SS.
	 */

	private int countSS;

	/**
	 * The total number of times the player has obtained a rank of SSH.
	 */

	private int countSSH;

	/**
	 * The total number of times the player has obtained a rank of S.
	 */

	private int countS;

	/**
	 * The total number of times the player has obtained a rank of SH.
	 */

	private int countSH;

	/**
	 * The total number of times the player has obtained a rank of A.
	 */

	private int countA;

	/**
	 * The country code of the player displayed as XY.
	 */

	private String country;

	/**
	 * The national leaderboard rank of the player determined by {@link #pp PP}.
	 */

	private int nationalRank;

	/**
	 * The URL to the players osu! user profile.
	 */

	private String profileUrl;

	/**
	 * See {@link Osu#getUser(String, OsuMode, Consumer, Consumer)}}
	 */

	public OsuUser(OsuMode mode, JSONObject object) {
		this.mode = mode;
		update(object);
		profileUrl = BASE_PROFILE_URL + userId;
	}

	public void update(JSONObject object) {
		userId = object.getInt("user_id");
		username = object.getString("username");
		count300 = object.optInt("count300", 0);
		count100 = object.optInt("count100", 0);
		count50 = object.optInt("count50", 0);
		playcount = object.optInt("playcount", 0);
		rankedScore = object.optLong("ranked_score", 0);
		totalScore = object.optLong("total_score", 0);
		rank = object.optInt("pp_rank", 0);
		level = object.optDouble("level", 0);
		pp = object.optDouble("pp_raw", 0.);
		accuracy = object.optDouble("accuracy", 0);
		countSS = object.optInt("count_rank_ss", 0);
		countSSH = object.optInt("count_rank_ssh", 0);
		countS = object.optInt("count_rank_s", 0);
		countSH = object.optInt("count_rank_sh", 0);
		countA = object.optInt("count_rank_a", 0);
		country = object.getString("country");
		nationalRank = object.getInt("pp_country_rank");
	}

	/**
	 * @return 	The id for the user. The ID never changes.
	 */

	public int getId() {
		return userId;
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
		return rankedScore;
	}

	/**
	 * @return 	The total score of the user, including unranked maps.
	 */

	public double getTotalScore() {
		return totalScore;
	}

	/**
	 * @return 	Get the users current leaderboard ranking, global.
	 */

	public int getRank() {
		return rank;
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

	public double getPp() {
		return pp;
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
		return countSS;
	}

	public int getCountSSH() {
		return countSSH;
	}

	/**
	 * @return 	Get total number of beatmaps with an S rank.
	 */

	public int getCountRankS() {
		return countS;
	}

	public int getCountSH() {
		return countSH;
	}

	/**
	 * @return 	Get total number of beatmaps with an A rank.
	 */

	public int getCountRankA() {
		return countA;
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

	public int getCountryRank() {
		return nationalRank;
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

	public String getAccuracyString() {
		return String.format("%05.2f%%", accuracy);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players total PP.
	 */

	public String getPpString() {
		return String.format("%,.2f", pp);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players ranked score.
	 */

	public String getRankedScoreString() {
		return String.format("%,d", rankedScore);
	}

	/**
	 * @return 	Returns a more aesthetic representation of the
	 * 			players total score including unranked.
	 */

	public String getTotalScoreString() {
		return String.format("%,d", totalScore);
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
		return String.format("ID: %d%nUsername: %s%nPP: %s", userId, username, getPpString());
	}
}
