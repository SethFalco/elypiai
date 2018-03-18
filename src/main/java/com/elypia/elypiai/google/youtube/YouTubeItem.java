package com.elypia.elypiai.google.youtube;

import com.elypia.elypiai.google.youtube.data.YouTubeType;
import org.json.JSONObject;

import java.time.Instant;

public class YouTubeItem {

	private YouTube youtube;
	private YouTubeType type;

	private String id;
	private Instant publishedDate;
	private String channelId;
	private String channelName;
	private String title;
	private String description;
	private String thumbnailLow;
	private String thumbailMedium;
	private String thumbnail;
	private boolean stream;
	private String url;

	public YouTubeItem(YouTube youtube, JSONObject object, YouTubeType type) {
		this.youtube = youtube;
		this.type = type;

		JSONObject idObj = object.getJSONObject("id");

		switch (type) {
			case VIDEO: {
				id = idObj.getString("videoId");
				url = YouTube.VIDEO_URL + id;
				break;
			}
			case PLAYLIST: {
				id = idObj.getString("playlistId");
				url = YouTube.PLAYLIST_URL + id;
				break;
			}
			case CHANNEL: {
				id = idObj.getString("channelId");
				url = YouTube.CHANNEL_URL + id;
				break;
			}
		}

		JSONObject snippet = object.getJSONObject("snippet");
		publishedDate = Instant.parse(snippet.getString("publishedAt"));
		channelId = snippet.getString("channelId");
		channelName = snippet.getString("channelTitle");
		title = snippet.getString("title");
		description = snippet.getString("description");
		stream = snippet.getString("liveBroadcastContent").equals("live");

		JSONObject thumbnails = snippet.getJSONObject("thumbnails");
		thumbnailLow = thumbnails.getJSONObject("default").getString("url");
		thumbailMedium = thumbnails.getJSONObject("medium").getString("url");
		thumbnail = thumbnails.getJSONObject("high").getString("url");
	}

	public YouTube getYouTube() {
		return youtube;
	}

	public YouTubeType getType() {
		return type;
	}

	/**
	 * @return	The identifier for the video or playlist.
	 */

	public String getId() {
		return id;
	}

	/**
	 * @return	Date the video was published.
	 */

	public Instant getPublishedDate() {
		return publishedDate;
	}

	/**
	 * This returns the identifier for the channel the media
	 * is under. See {@link #getChannelName()} for the channel name.
	 *
	 * @return	The identifier of the channel.
	 */

	public String getChannelId() {
		return channelId;
	}

	/**
	 * @return	The name of the media returned.
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * @return	The decription of the media returned.
	 */

	public String getDescription() {
		return description;
	}

	/**
	 * @return	Low quality thumbnail url. 120x90
	 */

	public String getDefaultThumbnail() {
		return thumbnailLow;
	}

	/**
	 * @return	Med quality thumbnail url. 320x180
	 */

	public String getMedThumbnail() {
		return thumbailMedium;
	}

	/**
	 * @return	High quality thumbnail url. 480x360
	 */

	public String getHighThumbnail() {
		return thumbnail;
	}

	/**
	 * @return	The name of the channel the media is under.
	 */

	public String getChannelName() {
		return channelName;
	}

	public boolean isStream() {
		return stream;
	}

	/**
	 * @return	The url to view the video or playlist.
	 */

	public String getUrl() {
		return url;
	}
}
