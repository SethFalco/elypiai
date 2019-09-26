/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.osu;

import com.google.gson.annotations.SerializedName;
import org.slf4j.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Player {

	private static final Logger logger = LoggerFactory.getLogger(Player.class);

	private static final String UNKNOWN_AVATAR = "https://osu.ppy.sh/images/layout/avatar-guest.png";

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
		return "https://osu.ppy.sh/u/" + userId;
	}

	public List<OsuEvent> getEvents() {
		return events;
	}

	/**
	 * This is a URL that will take you to the avatar
	 * of the player <strong>if it's set</strong> else
	 * it may be a dead link.
	 *
	 * @return	The profile picture of the osu player as a url.
	 */
	public String getAvatarUrl() {
		return getAvatarUrl(false);
	}

	/**
	 * This returns the avatar url checking if the url
	 * avatar exists in the first place and returns
	 * an apropriate default avatar for users that don't have one.
	 * <strong>Performs an HTTP HEAD request.</strong>
	 *
	 * @param check
	 * @return
	 */
	public String getAvatarUrl(boolean check) {
		if (avatarUrl != null)
			return avatarUrl;

		String url = "https://a.ppy.sh/" + userId;

		if (!check)
			avatarUrl = url;

		else {
			try {
				var con = (HttpsURLConnection) new URL(url).openConnection();
				con.setRequestMethod("HEAD");

				if (con.getResponseCode() == HttpsURLConnection.HTTP_OK)
					return avatarUrl = url;
			} catch (IOException ex) {
				logger.error("Failed to verify osu! avatar.", ex);
			}
		}

		return avatarUrl = UNKNOWN_AVATAR;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Player))
			return false;

		Player player = (Player)object;

		return
			userId == player.userId &&
			username.equals(player.username) &&
			count300 == player.count300 &&
			count100 == player.count100 &&
			count50 == player.count50 &&
			playcount == player.playcount &&
			rankedScore == player.rankedScore &&
			totalScore == player.totalScore &&
			rank == player.rank &&
			level == player.level &&
			pp == player.pp &&
			accuracy == player.accuracy &&
			countSS == player.countSS &&
			countSSH == player.countSSH &&
			countS == player.countS &&
			countSH == player.countSH &&
			countA == player.countA &&
			country.equals(player.country) &&
			nationalRank == player.nationalRank;
	}
}
