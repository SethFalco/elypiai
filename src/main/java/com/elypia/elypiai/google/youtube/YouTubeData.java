package com.elypia.elypiai.google.youtube;

import org.json.JSONObject;

public class YouTubeData {

	private String kind;
	private String id;
	private String publishedAt;
	private String channelId;
	private String title;
	private String desc;
	private String thumbDef;
	private String thumbMed;
	private String thumbHigh;
	private String channelTitle;
	private String liveBroadcastContent;
	private String mediaUrl;

	YouTubeData(JSONObject object, YouTubeType type) {
		object = object.getJSONArray("items").getJSONObject(0);

		switch (type) {
			case VIDEO: {
				id = object.getJSONObject("id").getString("videoId");
				mediaUrl = YouTube.VIDEO_URL + id;
				break;
			}
			case PLAYLIST: {
				id = object.getJSONObject("id").getString("playlistId");
				mediaUrl = YouTube.PLAYLIST_URL + id;
				break;
			}
			case CHANNEL: {
				id = object.getJSONObject("id").getString("channelId");
				mediaUrl = YouTube.CHANNEL_URL + id;
				break;
			}
		}

		kind = object.getString("kind");

		object = object.getJSONObject("snippet");
		publishedAt = object.getString("publishedAt");
		channelId = object.getString("channelId");
		title = object.getString("title");
		desc = object.getString("description");
		channelTitle = object.getString("channelTitle");
		liveBroadcastContent = object.getString("liveBroadcastContent");

		object = object.getJSONObject("thumbnails");
		thumbDef = object.getJSONObject("default").getString("url");
		thumbMed = object.getJSONObject("medium").getString("url");
		thumbHigh = object.getJSONObject("high").getString("url");
	}

	/**
	 * @return	The API resource type.
	 */

	public String getKind() {
		return kind;
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

	public String getPublishedAt() {
		return publishedAt;
	}

	/**
	 * This returns the identifier for the channel the media
	 * is under. See {@link #getChannelTitle()} for the channel name.
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
		return desc;
	}

	/**
	 * @return	Low quality thumbnail url. 120x90
	 */

	public String getDefaultThumbnail() {
		return thumbDef;
	}

	/**
	 * @return	Med quality thumbnail url. 320x180
	 */

	public String getMedThumbnail() {
		return thumbMed;
	}

	/**
	 * @return	High quality thumbnail url. 480x360
	 */

	public String getHighThumbnail() {
		return thumbHigh;
	}

	/**
	 * @return	The name of the channel the media is under.
	 */

	public String getChannelTitle() {
		return channelTitle;
	}

	public String getLiveBroadcastContent() {
		return liveBroadcastContent;
	}

	/**
	 * @return	The url to view the video or playlist.
	 */

	public String getUrl() {
		return mediaUrl;
	}
}
