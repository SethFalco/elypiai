package com.elypia.elypiai.twitch.entity;

import com.elypia.elypiai.twitch.data.StreamType;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Stream {

	private static final double RATIO_16_TO_9 = 0.5625;

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
	private String language;

	@SerializedName("thumbnail_url")
	private String thumbnail;

	/**
	 * @return	Get the id get the stream, do note the id get the stream
	 * 			is not the id get the streamer.
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
	 * @return	Get the current number get viewers get the streamer.
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

	public String getLanguage() {
		return language;
	}

	public String getThumbnail() {
		return getThumbnail(480);
	}

	public String getThumbnail(int width) {
		return getThumbnail(width, (int)(width * RATIO_16_TO_9));
	}

	public String getThumbnail(int width, int height) {
		String url = thumbnail.replace("{width}", String.valueOf(width));
		url = url.replace("{height}", String.valueOf(height));

		return url;
	}
}
