package com.elypia.elypiai.osu;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Player {

	private static final String BASE_PROFILE_URL = "https://osu.ppy.sh/u/%s";
	private static final String DEFAULT_AVATAR = "https://osu.ppy.sh/images/layout/avatar-guest.png";

	/**
	 * The identifier for the user, this will never change.
	 */
	@SerializedName("user_id")
	private int userId;

	/**
	 * The username of the player, this can be changed so is not
	 * good for storing identity of the player and should just be
	 * for display purposes. See {@link #userId} for an identifier.
	 */
	@SerializedName("username")
	private String username;

	/**
	 * The number of times the player has clicked and scored a 300.
	 */
	@SerializedName("count300")
	private int count300;

	/**
	 * The number of times the player has clicked and scored a 100.
	 */
	@SerializedName("count100")
	private int count100;

	/**
	 * The number of times the player has clicked and scored a 50.
	 */
	@SerializedName("count50")
	private int count50;

	/**
	 * The total number of songs the user has played altogether.
	 */
	@SerializedName("playcount")
	private int playcount;

	/**
	 * The total score the player has obtained from ranked maps only.
	 */
	@SerializedName("ranked_score")
	private long rankedScore;

	/**
	 * The total score the player has obtained on any map.
	 */
	@SerializedName("total_score")
	private long totalScore;

	/**
	 * The leaderboard ranking of the player determined by {@link #pp PP}.
	 */
	@SerializedName("pp_rank")
	private int rank;

	/**
	 * The level of the player.
	 */
	@SerializedName("level")
	private double level;

	/**
	 * The total PP of the player, obtained from playing ranked maps.
	 */
	@SerializedName("pp_raw")
	private double pp;

	/**
	 * The average accuracy of the player, formatted as: 92.789.
	 */
	@SerializedName("accuracy")
	private double accuracy;

	/**
	 * The total number of times the player has obtained a rank of SS.
	 */
	@SerializedName("count_rank_ss")
	private int countSS;

	/**
	 * The total number of times the player has obtained a rank of SSH.
	 */
	@SerializedName("count_rank_ssh")
	private int countSSH;

	/**
	 * The total number of times the player has obtained a rank of S.
	 */
	@SerializedName("count_rank_s")
	private int countS;

	/**
	 * The total number of times the player has obtained a rank of SH.
	 */
	@SerializedName("count_rank_sh")
	private int countSH;

	/**
	 * The total number of times the player has obtained a rank of A.
	 */
	@SerializedName("count_rank_a")
	private int countA;

	/**
	 * The country code of the player displayed as XY.
	 */
	@SerializedName("country")
	private String country;

	/**
	 * The national leaderboard rank of the player determined by {@link #pp PP}.
	 */
	@SerializedName("pp_country_rank")
	private int nationalRank;

	@SerializedName("events")
	private List<OsuEvent> events;

	private String avatarUrl;

	/**
	 * @return 	The id for the user. The ID never changes.
	 */
	public int getId() {
		return userId;
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
	public long getTotalScore() {
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
	public int getCountSS() {
		return countSS;
	}

	public int getCountSSH() {
		return countSSH;
	}

	/**
	 * @return 	Get total number of beatmaps with an S rank.
	 */
	public int getCountS() {
		return countS;
	}

	public int getCountSH() {
		return countSH;
	}

	/**
	 * @return 	Get total number of beatmaps with an A rank.
	 */
	public int getCountA() {
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
		return String.format(BASE_PROFILE_URL, userId);
	}

	public List<OsuEvent> getEvents() {
		return events;
	}

	/**
	 * @return	The profile picture of the osu player as a url.
	 */
	public String getAvatarUrl() {
		if (avatarUrl == null) {
			try {
				Document doc = Jsoup.parse(new URL(getProfileUrl()), 4096);
				Element element = doc.getElementById("json-user");

				if (element != null) {
					String text = element.childNode(0).toString();
					JsonObject object = new JsonParser().parse(text).getAsJsonObject();
					String url = object.get("avatar_url").getAsString();
					return avatarUrl = url;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return DEFAULT_AVATAR;
	}
}
