package com.elypia.elypiai.steam;

import com.elypia.elypiai.steam.data.PersonaState;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

public class SteamUser {

	/**
	 * The parent Steam instance that created this User.
	 */

	private Steam steam;

	/**
	 * 64bit SteamID of the user
	 */

	private long id;

	/**
	 * The player's persona name (display name)
	 */

	private String username;

	/**
	 * The full URL of the player's Steam Community profile.
	 */

	private String url;

	/**
	 * The full URL of the player's 184x184px avatar.
	 * If the user hasn't configured an avatar,
	 * this will be the default ? avatar.
	 */

	private String avatar;

	/**
	 * The user's current status. If the player's profile is private,
	 * this will always be "0".
	 */

	private PersonaState state;

	/**
	 * This represents whether the profile is visible or not.
	 */

	private boolean isPrivate;

	/**
	 * Indicates the user has a community profile configured.
	 */

	private boolean hasProfile;

	/**
	 * The last time the user was online.
	 */

	private Instant lastLogOff;

	/**
	 * If set, indicates the profile allows public comments.
	 */

	private boolean canComment;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The player's "Real Name", if they have set it.
	 */

	private String realName;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The player's primary group, as configured in their Steam Community profile.
	 */

	private String primaryClan;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The time the player's account was created.
	 */

	private double timeCreated;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * If the user is currently in-game,
	 * this value will be returned and set to the gameid of that game.
	 */

	public int gameId;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The ip and port of the game server the user is currently playing on,
	 * if they are playing on-line in a game using Steam matchmaking.
	 */

	public String serverAddress;

	/**
	 * If the user is currently in-game,
	 * this will be the name of the game they are playing.
	 * This may be the name of a non-Steam game shortcut.
	 */

	public String gameStatus;

	/**
	 * If set on the user's Steam Community profile,
	 * The user's country of residence, 2-character ISO country code
	 */

	private String countryCode;

	/**
	 * If set on the user's Steam Community profile, The user's state of residence
	 */

	private String stateCode;

	/**
	 * See {@link Steam#getUser(String, Consumer, Consumer)}
	 */

	public SteamUser(Steam steam, JSONObject object) {
		this.steam = steam;

		id = object.optLong("steamid");
		state = PersonaState.values()[object.getInt("personastate")];
		lastLogOff = Instant.ofEpochMilli(object.getLong("lastlogoff"));
		hasProfile = object.getInt("profilestate") == 1;
		avatar = object.getString("avatarfull");
		url = object.getString("profileurl");
		username = object.getString("personaname");
		isPrivate = object.getInt("communityvisibilitystate") == 1;

		// Information is only available if the profile isn't private.
		if (!isPrivate) {
			realName = object.optString("realname", null);
			primaryClan = object.getString("primaryclanid");
			timeCreated = object.getDouble("timecreated");
			countryCode = object.optString("loccountrycode", null);
			stateCode = object.optString("locstatecode", null);
		}
	}

	public Steam getSteam() {
		return steam;
	}

	/**
	 * @return	Returns the ID of the user. See {@link #getUsername()}
	 * 			for name of the user instead.
	 */

	public long getId() {
		return id;
	}

	public boolean hasProfile() {
		return hasProfile;
	}

	/**
	 * @return	Returns the name of the user as displayed
	 * 			on their Steam profile.
	 */

	public String getUsername() {
		return username;
	}

	/**
	 * @return	The last time the user was logged on.
	 */

	public Instant getLastLogOff() {
		return lastLogOff;
	}

	/**
	 * @return	Return the Url to their steam profile.
	 */

	public String getProfileURL() {
		return url;
	}

	/**
	 * @return	The full URL of the player's 184x184px avatar.
	 * 			If the user hasn't configured an avatar, this will be the default ? avatar.
	 */

	public String getAvatar() {
		return avatar;
	}


	/**
	 * @return	The users personal state/status. Eg, Online, Offline,
	 * 			Busy, Away etcetc. See {@link PersonaState}.
	 */

	public PersonaState getPersonaState() {
		return state;
	}

	/**
	 * @return	Returns the real name of the user.
	 * 			Possible null; if userprofile is private.
	 */

	public String getRealName() {
		return realName;
	}

	/**
	 * @return	Returns the id for the users primary clan.
	 * 			Possible null; if userprofile is private.
	 */

	public String getPrimaryClanId() {
		return primaryClan;
	}

	/**
	 * @return	The date and time the users profile was created
	 * 			in UNIX time.
	 * 			Possible null; if userprofile is private.
	 */

	public double getTimeCreated() {
		return timeCreated;
	}

	/**
	 * @return	Returns country code of the user.
	 * 			Possible null; if userprofile is private or
	 * 			simply not set by the user.
	 */

	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @return	The users state of residence.
	 * 			Possible null; if userprofile is private or
	 * 			simply not set by the user.
	 */

	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @return	If the users profile is private.
	 * 			Returns true of profile is private or
	 * 			set for friends only.
	 */

	public boolean isPrivate() {
		return isPrivate;
	}

	public void getLibrary(Consumer<List<SteamGame>> success, Consumer<IOException> failure) {
		steam.getLibrary(this, success, failure);
	}
}
