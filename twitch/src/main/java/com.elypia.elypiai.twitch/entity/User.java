package com.elypia.elypiai.twitch.entity;

import com.elypia.elypiai.common.gson.deserializers.EmptyNullDeserializer;
import com.elypia.elypiai.twitch.StreamPaginator;
import com.elypia.elypiai.twitch.Twitch;
import com.elypia.elypiai.twitch.TwitchQuery;
import com.elypia.elypiai.twitch.data.AccountType;
import com.elypia.elypiai.twitch.data.BroadcasterType;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

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
