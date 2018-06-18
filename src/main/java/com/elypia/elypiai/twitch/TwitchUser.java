package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.*;
import com.google.gson.JsonObject;

public class TwitchUser {

	private static final String TWITCH_URL = "https://www.twitch.tv/";

	private Twitch twitch;

	private int id;
	private String username;
	private String displayName;
	private AccountType type;
	private BroadcasterType broadcasterType;
	private String description;
	private String avatarUrl;
	private String offlineImageUrl;
	private int viewCount;
	private String url;

	public TwitchUser(Twitch twitch, JsonObject object) {
		this.twitch = twitch;

		id = object.get("id").getAsInt();
		username = object.get("login").getAsString();
		displayName = object.get("display_name").getAsString();
		type = AccountType.getByName(object.get("type").getAsString());
		broadcasterType = BroadcasterType.getByName(object.get("broadcaster_type").getAsString());
		avatarUrl = object.get("profile_image_url").getAsString();
		offlineImageUrl = object.get("offline_image_url").getAsString();
		viewCount = object.get("view_count").getAsInt();

		description = object.get("description").getAsString();

		if (description.isEmpty())
			description = null;

		url = TWITCH_URL + username;
	}

	public Twitch getTwitch() {
		return twitch;
	}

	/**
	 * @return	Get the id of the user.
	 */

	public int getId() {
		return id;
	}

	/**
	 * @return	Return the username of the user, all lower case.
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * @return	Get's the display name of the user
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
	 * @return	Returns the bio of the user.
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
		return url;
	}
}
