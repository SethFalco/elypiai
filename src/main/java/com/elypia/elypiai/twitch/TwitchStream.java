package com.elypia.elypiai.twitch;

import org.json.JSONObject;

public class TwitchStream {

	private long streamId;
	private String game;
	private int viewers;
	private int videoHeight;
	private int fps;
	private int delay;
	private String streamCreatedAt;
	private boolean isPlaylist;
	private String thumbnail;

	/**
	 * {@link TwitchUser#getStream()}
	 */

	public TwitchStream(JSONObject object) {
		update(object);
	}

	public void update(JSONObject object) {
		streamId 		= object.getLong("_id");
		viewers  		= object.getInt("viewers");
		videoHeight 	= object.getInt("video_height");
		fps 			= object.getInt("average_fps");
		delay 			= object.getInt("delay");
		streamCreatedAt = object.getString("created_at");
		game     		= object.getString("game");
		isPlaylist 		= object.getBoolean("is_playlist");
		thumbnail 		= object.getJSONObject("preview").getString("large");
	}

	/**
	 * @return	Get the id of the stream, do not
	 * 			the id of the stream is not the
	 * 			id of the streamer.
	 */

	public long getStreamId() {
		return streamId;
	}

	/**
	 * @return	Get the game the streamer is currently playing.
	 */

	public String getGame() {
		return game;
	}

	/**
	 * @return	Get the current number of viewers of the streamer.
	 */

	public int getViewers() {
		return viewers;
	}

	/**
	 * @return	Get the video height of the stream.
	 */

	public int getVideoHeight() {
		return videoHeight;
	}

	/**
	 * @return	Get the FPS of the stream.
	 */

	public int getFPS() {
		return fps;
	}

	/**
	 * @return	Get the delay of the stream.
	 */

	public int getDelay() {
		return delay;
	}

	/**
	 * @return	Get the time and date the user started streaming.
	 */

	public String getStreamCreatedDate() {
		return streamCreatedAt;
	}

	public boolean isPlaylist() {
		return isPlaylist;
	}

	/**
	 * @return	Get the thumbnail of the stream in the form of a url.
	 */

	public String getThumbnail() {
		return thumbnail;
	}
}
