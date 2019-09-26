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

package org.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.twitch.data.StreamType;

import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class TwitchStream {

	private static final double RATIO_16_TO_9 = 0.5625;

	@SerializedName("id")
	private long id;

	@SerializedName("user_id")
	private int userId;

	@SerializedName("user_name")
	private String username;

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

	public String getUsername() {
		return username;
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
