package com.elypia.elypiai.steam;

import org.json.JSONObject;

public class SteamUser {

	private long steamid;
	private int profilestate;
	private String name;
	private double lastlogoff;
	private String profileurl;
	private String avatar;
	private String avatarMed;
	private String avatarFull;
	private SteamUserState state;
	private String realName;
	private String primaryClan;
	private double timeCreated;
	private int flags;
	private String locCountryCode;
	private String stateCode;
	private boolean isPrivate;

	/**
	 * See {@link Steam#getUser(String)}
	 */

	public SteamUser(JSONObject object) {
		steamid		 = object.optLong("steamid");
		state		 = SteamUserState.values()[object.getInt("personastate")];
		lastlogoff 	 = object.getDouble("lastlogoff");
		profilestate = object.getInt("profilestate");
		flags		 = object.getInt("personastateflags");
		avatarMed 	 = object.getString("avatarmedium");
		avatarFull 	 = object.getString("avatarfull");
		profileurl 	 = object.getString("profileurl");
		avatar 		 = object.getString("avatar");
		name 		 = object.getString("personaname");
		isPrivate 	 = object.getInt("communityvisibilitystate") == 1;

		// Information is only available if the profile isn't private.
		if (!isPrivate) {
			realName  	   = object.optString("realname", null);
			primaryClan    = object.getString("primaryclanid");
			timeCreated    = object.getDouble("timecreated");
			locCountryCode = object.optString("loccountrycode", null);
			stateCode 	   = object.optString("locstatecode", null);
		}
	}

	/**
	 * @return	Returns the ID of the user. See {@link #getName()}
	 * 			for name of the user instead.
	 */

	public long getSteamId() {
		return steamid;
	}

	public int getProfileState() {
		return profilestate;
	}

	/**
	 * @return	Returns the name of the user as displayed
	 * 			on their Steam profile.
	 */

	public String getName() {
		return name;
	}

	/**
	 * @return	The last time the user was logged on, in UNIX time.
	 */

	public double getLastLogOff() {
		return lastlogoff;
	}

	/**
	 * @return	Return the Url to their steam profile.
	 */

	public String getProfileURL() {
		return profileurl;
	}

	/**
	 * @return	Get low quality version of profile picture. (32x32)
	 * 			See {@link #getAvatarFull()} for full quality.
	 */

	public String getAvatar() {
		return avatar;
	}

	/**
	 * @return	Get medium quality version of profile picture. (64x64)
	 * 			See {@link #getAvatarFull()} for full quality.
	 */

	public String getAvatarMed() {
		return avatarMed;
	}

	/**
	 * @return	Get high quality version of profile picture. (184x184)
	 */

	public String getAvatarFull() {
		return avatarFull;
	}

	/**
	 * @return	The users personal state/status. Eg, Online, Offline,
	 * 			Busy, Away etcetc. See {@link SteamUserState}.
	 */

	public SteamUserState getPersonaState() {
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

	public String getPrimaryClanID() {
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

	public int getFlags() {
		return flags;
	}

	/**
	 * @return	Returns country code of the user.
	 * 			Possible null; if userprofile is private or
	 * 			simply not set by the user.
	 */

	public String getCountryCode() {
		return locCountryCode;
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
}
