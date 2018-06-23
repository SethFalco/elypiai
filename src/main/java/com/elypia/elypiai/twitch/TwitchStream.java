package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.StreamType;
import org.json.*;

import java.time.Instant;

public class TwitchStream {

	private Twitch twitch;
	private TwitchUser user;

	private long id;
	private int gameId;
	private String[] communityIds;
	private StreamType type;
	private String title;
	private int viewerCount;
	private Instant startDate;
	private String language;
	private String thumbnail;

	public TwitchStream(TwitchUser user, JSONObject object) {
		this.user = user;
		twitch = user.getTwitch();

		update(object);
	}

	public void update(JSONObject object) {
		id = object.optLong("id");
		gameId = object.optInt("game_id");
		type = StreamType.getByName(object.getString("type"));
		title = object.getString("title");
		viewerCount = object.getInt("viewer_count");
		startDate = Instant.parse(object.getString("started_at"));
		language = object.getString("language");
		thumbnail = object.getString("thumbnail_url");

		JSONArray array = object.getJSONArray("community_ids");
		communityIds = array.toList().toArray(new String[0]);
	}


	public Twitch getTwitch() {
		return twitch;
	}

	public TwitchUser getUser() {
		return user;
	}

	/**
	 * @return	Get the id of the stream, do not
	 * 			the id of the stream is not the
	 * 			id of the streamer.
	 */

	public long getId() {
		return id;
	}

	/**
	 * @return	Get the game the streamer is currently playing.
	 */

	public int getGameId() {
		return gameId;
	}

	public String[] getCommunityIds() {
		return communityIds;
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

	public Instant getStartDate() {
		return startDate;
	}

	public String getLanguage() {
		return language;
	}

	public String getThumbnail() {
		return getThumbnail(1600);
	}

	public String getThumbnail(int width) {
		return getThumbnail(width, width / 16 * 9);
	}

	public String getThumbnail(int width, int height) {
		String url = thumbnail.replace("{width}", String.valueOf(width));
		url = url.replace("{height}", String.valueOf(height));

		return url;
	}
}
