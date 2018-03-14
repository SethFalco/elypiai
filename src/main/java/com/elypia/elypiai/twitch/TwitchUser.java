package com.elypia.elypiai.twitch;

import org.json.JSONObject;

public class TwitchUser {
	private static final String TWITCH_URL = "https://www.twitch.tv/";

	private String displayName;
	private int id;
	private String name;
	private String type;
	private String bio;
	private String createdAt;
	private String updatedAt;
	private String logo;
	private String url;
	private TwitchStream stream;

	public TwitchUser(JSONObject user) {
		displayName = user.getString("display_name");
		id 			= user.optInt("_id");
		name 		= user.getString("name");
		type 		= user.getString("type");
		createdAt 	= user.getString("created_at");
		updatedAt 	= user.getString("updated_at");
		bio 		= user.optString("bio", null);
		logo 		= user.optString("logo", null);
		url 		= TWITCH_URL + displayName;
	}

	public void setStreamInfo(JSONObject object) {
		if (object == null) {
			stream = null;
		} else {
			if (stream == null)
				stream = new TwitchStream(object);
			else
				stream.update(object);
		}
	}

	/**
	 * @return	Get's the display name of the user
	 * 			including capitilisation.
	 */

	public String getDisplayName() {
		return displayName;
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

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	/**
	 * @return	Returns the bio of the user.
	 * 			Possible null, if user hasn't set a bio.
	 */

	public String getBio() {
		return bio;
	}

	/**
	 * @return	The date the user created their account.
	 */

	public String getCreatedDate() {
		return createdAt;
	}

	public String getUpdatedDate() {
		return updatedAt;
	}

	/**
	 * @return	The users profile picture. Possible
	 * 			null; if user doesn't have a profile picture.
	 */

	public String getLogo() {
		return logo;
	}

	/**
	 * @return	The url of the streamers channel.
	 */

	public String getUrl() {
		return url;
	}

	/**
	 * @return	Return the stream object containing any information
	 * 			on their current stream. Possible null; if
	 * 			not streaming.
	 */

	public TwitchStream getStream() {
		return stream;
	}

	/**
	 * @return	If the user is live or not.
	 */

	public boolean isLive() {
		return stream != null;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
