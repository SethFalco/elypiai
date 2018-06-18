package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.StreamType;
import com.google.gson.*;

import java.time.Instant;
import java.util.*;

public class TwitchStream {

	private Twitch twitch;
	private TwitchUser user;

	private long id;
	private int gameId;
	private Collection<String> communityIds;
	private StreamType type;
	private String title;
	private int viewerCount;
	private Instant startDate;
	private String language;
	private String thumbnail;

	public TwitchStream(TwitchUser user, JsonObject object) {
		this.user = user;
		twitch = user.getTwitch();

		update(object);
	}

	public void update(JsonObject object) {
		id = object.get("id").getAsInt();
		gameId = object.get("game_id").getAsInt();
		type = StreamType.getByName(object.get("type").getAsString());
		title = object.get("title").getAsString();
		viewerCount = object.get("viewer_count").getAsInt();
		startDate = Instant.parse(object.get("started_at").getAsString());
		language = object.get("language").getAsString();
		thumbnail = object.get("thumbnail_url").getAsString();

		communityIds = new ArrayList<>();

		JsonArray array = object.get("community_ids").getAsJsonArray();
		array.forEach(o -> communityIds.add(o.getAsString()));
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

	public Collection<String> getCommunityIds() {
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
