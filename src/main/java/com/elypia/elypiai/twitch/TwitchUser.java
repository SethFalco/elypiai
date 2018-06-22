package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.*;
import org.json.JSONObject;

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

	public TwitchUser(Twitch twitch, JSONObject object) {
		this.twitch = twitch;

		id = object.optInt("id");
		username = object.getString("login");
		displayName = object.getString("display_name");
		type = AccountType.getByName(object.getString("type"));
		broadcasterType = BroadcasterType.getByName(object.getString("broadcaster_type"));
		avatarUrl = object.getString("profile_image_url");
		offlineImageUrl = object.getString("offline_image_url");
		viewCount = object.optInt("view_count");

		description = object.getString("description");

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
