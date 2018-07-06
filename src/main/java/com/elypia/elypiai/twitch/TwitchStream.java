package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.StreamType;
import com.elypia.elypiai.utils.Language;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class TwitchStream {

	@SerializedName("id")
	private long id;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("game_id")
	private int gameId;

	@SerializedName("community_ids")
	private List<String> communityIds;

	@SerializedName("type")
	private StreamType type;

	@SerializedName("title")
	private String title;

	@SerializedName("viewer_count")
	private int viewerCount;

	@SerializedName("started_at")
	private Date startDate;

	@SerializedName("language")
	private Language language;

	@SerializedName("thumbnail_url")
	private String thumbnail;

	private String cursor;

	/**
	 * @return	Get the id of the stream, do note the id of the stream
	 * 			is not the id of the streamer.
	 */

	public long getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	/**
	 * @return	Get the game the streamer is currently playing.
	 */

	public int getGameId() {
		return gameId;
	}

	public List<String> getCommunityIds() {
		return Collections.unmodifiableList(communityIds);
	}

	public StreamType getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * @return	Get the current number of viewers of the streamer.
	 */

	public int getViewerCount() {
		return viewerCount;
	}

	/**
	 * @return	Get the time and date the user started streaming.
	 */

	public Date getStartDate() {
		return startDate;
	}

	public Language getLanguage() {
		return language;
	}

	public String getThumbnail() {
		return getThumbnail(480);
	}

	public String getThumbnail(int width) {
		return getThumbnail(width, width / 16 * 9);
	}

	public String getThumbnail(int width, int height) {
		String url = thumbnail.replace("{width}", String.valueOf(width));
		url = url.replace("{height}", String.valueOf(height));

		return url;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
}
