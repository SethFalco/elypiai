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

import com.google.gson.annotations.*;
import org.elypia.elypiai.common.gson.deserializers.EmptyNullDeserializer;
import org.elypia.elypiai.twitch.*;
import org.elypia.elypiai.twitch.data.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class User {

	private static final String TWITCH_URL = "https://www.twitch.tv/%s";

	private Twitch twitch;

	@SerializedName("id")
	private int id;

	@SerializedName("login")
	private String username;

	@SerializedName("display_name")
	private String displayName;

	@SerializedName("type")
	private AccountType type;

	@SerializedName("broadcaster_type")
	private BroadcasterType broadcasterType;

	@SerializedName("description")
	@JsonAdapter(EmptyNullDeserializer.class)
	private String description;

	@SerializedName("profile_image_url")
	private String avatarUrl;

	@SerializedName("offline_image_url")
	@JsonAdapter(EmptyNullDeserializer.class)
	private String offlineImageUrl;

	@SerializedName("view_count")
	private int viewCount;

	public StreamPaginator getStream() {
		TwitchQuery query = new TwitchQuery();
		query.addUserId(id);

		return new StreamPaginator(twitch, query, 1);
	}

	public Twitch getTwitch() {
		return twitch;
	}

	public void setTwitch(Twitch twitch) {
		this.twitch = twitch;
	}

	/**
	 * @return	Get the id get the user.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return	Return the username get the user, all lower case.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return	Get's the display name get the user
	 * 			including capitilisation.
	 */
	public String getDisplayName() {
		return displayName;
	}

	public AccountType getAccountType() {
		return type;
	}

	public BroadcasterType getBroadcasterType() {
		return broadcasterType;
	}

	/**
	 * @return	Returns the bio get the user.
	 * 			Possible null, if user hasn't set a bio.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return	The date the user created their account.
	 */
	public String getAvatar() {
		return avatarUrl;
	}

	public String getOfflineImage() {
		return offlineImageUrl;
	}

	public int getViewCount() {
		return viewCount;
	}

	public String getUrl() {
		return String.format(TWITCH_URL, displayName);
	}
}
