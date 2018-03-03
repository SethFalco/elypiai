package com.elypia.elypiai.steam;

import com.elypia.elypiai.utils.ElyUtils;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class SteamGame implements Comparable<SteamGame> {

	public static final String GAME_URL_FORMAT = "http://store.steampowered.com/app/%d";
	public static final String IMAGE_FORMAT = "http://media.steampowered.com/steamcommunity/public/images/apps/%s/%s.jpg";

	private SteamUser user;
	private int id;
	private String url;
	private String name;
	private long totalPlaytime;
	private long recentPlaytime;
	private String imgIconUrl;
	private String imgLogoUrl;
	private boolean communityVisibleStats;

	public SteamGame(SteamUser user, JSONObject game) {
		this.user = user;
		id = game.getInt("appid");
		url = String.format(GAME_URL_FORMAT, id);
		name = game.getString("name");
		totalPlaytime = game.optLong("playtime_forever", 0);
		recentPlaytime = game.optLong("playtime_2weeks", 0);
		imgIconUrl = String.format(IMAGE_FORMAT, id, game.getString("img_icon_url"));
		imgLogoUrl = String.format(IMAGE_FORMAT, id, game.getString("img_logo_url"));
		communityVisibleStats = game.optBoolean("has_community_visible_stats", false);
	}

	public SteamUser getUser() {
		return user;
	}

	/**
	 * @return	Returns the appid of the game.
	 */

	public int getId() {
		return id;
	}

	public String getGameUrl() {
		return url;
	}

	/**
	 * @return	Returns the name of the game.
	 */

	public String getName() {
		return name;
	}


	public long getTotalPlaytime(TimeUnit unit) {
		return ElyUtils.convertTime(totalPlaytime, TimeUnit.MINUTES, unit);
	}

	public long getRecentPlaytime(TimeUnit unit) {
		return ElyUtils.convertTime(recentPlaytime, TimeUnit.MINUTES, unit);
	}

	public String getIconUrl() {
		return imgIconUrl;
	}

	public String getLogoUrl() {
		return imgLogoUrl;
	}

	public boolean isCommunityStatsVisible() {
		return communityVisibleStats;
	}

	/**
	 * Can use Collections#Sort method to sort List of SteamGames
	 * This returns the list in order of RecentPlaytime, and once
	 * RecentPlaytime is 0, of TotalPlaytime.
	 */

	@Override
	public int compareTo(SteamGame game) {
		if (game.recentPlaytime == recentPlaytime)
			return game.totalPlaytime > totalPlaytime ? 1 : -1;

		return (game.recentPlaytime > recentPlaytime) ? 1 : -1;
	}
}
