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

package org.elypia.elypiai.steam;

import com.google.gson.annotations.*;
import org.elypia.elypiai.common.gson.deserializers.*;
import org.elypia.elypiai.steam.data.PersonaState;

import java.util.Date;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class SteamUser {

	private Steam steam;

	/**
	 * 64bit SteamID of the user
	 */
	@SerializedName("steamid")
	private long id;

	/**
	 * The player's persona name (display name)
	 */
	@SerializedName("personaname")
	private String username;

	/**
	 * The full URL of the player's Steam Community profile.
	 */
	@SerializedName("profileurl")
	private String url;

	@SerializedName("avatar")
	private String avatarLow;

	/**
	 * The full URL of the player's 184x184px avatar.
	 * If the user hasn't configured an avatar,
	 * this will be the default ? avatar.
	 */
	@SerializedName("avatarmedium")
	private String avatarMedium;

	@SerializedName("avatarfull")
	private String avatarHigh;

	/**
	 * The user's current status. If the player's profile is private,
	 * this will always be "0".
	 */
	@SerializedName("personastate")
	private PersonaState state;

	/**
	 * This represents whether the profile is visible or not.
	 */
	@SerializedName("communityvisibilitystate")
	@JsonAdapter(BitBooleanDeserializer.class)
	private boolean isPrivate;

	/**
	 * Indicates the user has a community profile configured.
	 */
	@SerializedName("profilestate")
	@JsonAdapter(BitBooleanDeserializer.class)
	private boolean hasProfile;

	/**
	 * The last time the user was online.
	 */
	@SerializedName("lastlogoff")
	@JsonAdapter(DateDeserializer.class)
	private Date lastLogOff;

	/**
	 * If set, indicates the profile allows public comments.
	 */
	@SerializedName("commentpermission")
	@JsonAdapter(BitBooleanDeserializer.class)
	private boolean canComment;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The player's "Real Name", if they have set it.
	 */
	@SerializedName("realname")
	private String realName;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The player's primary group, as configured in their Steam Community profile.
	 */
	@SerializedName("primaryclanid")
	private long primaryClan;

	/**
	 * <strong>Only non-null if profile is not private.</strong><br>
	 * The time the player's account was created.
	 */

	@SerializedName("timecreated")
	@JsonAdapter(DateDeserializer.class)
	private Date timeCreated;

	/**
	 * If set on the user's Steam Community profile,
	 * The user's country of residence, 2-character ISO country code
	 */
	@SerializedName("loccountrycode")
	private String countryCode;

	/**
	 * If set on the user's Steam Community profile, The user's state of residence
	 */
	@SerializedName("locstatecode")
	private String stateCode;

	@SerializedName("loccityid")
	private int cityId;

	private GameSession currentlyPlaying;

	public Steam getSteam() {
		return steam;
	}

	public void setSteam(Steam steam) {
		this.steam = steam;
	}

	/**
	 * @return	Returns the ID of the user. See {@link #getUsername()}
	 * 			for name of the user instead.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return	Returns the name of the user as displayed
	 * 			on their Steam profile.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return	Return the Url to their steam profile.
	 */
	public String getProfileUrl() {
		return url;
	}

	public String getAvatarLow() {
		return avatarLow;
	}

	public String getAvatarMedium() {
		return avatarMedium;
	}

	/**
	 * @return	The full URL of the player's 184x184px avatar.
	 * 			If the user hasn't configured an avatar, this will be the default ? avatar.
	 */
	public String getAvatarHigh() {
		return avatarHigh;
	}

	/**
	 * @return	The users personal state/status. Eg, Online, Offline,
	 * 			Busy, Away etcetc. See {@link PersonaState}.
	 */
	public PersonaState getPersonaState() {
		return state;
	}

	/**
	 * @return	If the users profile is private.
	 * 			Returns true of profile is private or
	 * 			set for friends only.
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	public boolean hasProfile() {
		return hasProfile;
	}

	public Date getLastLogOff() {
		return lastLogOff;
	}

	public boolean canComment() {
		return canComment;
	}

	public String getRealName() {
		return realName;
	}

	public long getPrimaryClan() {
		return primaryClan;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public String getCountry() {
		return countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public int getCityId() {
		return cityId;
	}

	public GameSession getCurrentlyPlaying() {
		return currentlyPlaying;
	}

	public void setCurrentlyPlaying(GameSession currentlyPlaying) {
		this.currentlyPlaying = currentlyPlaying;
	}
}
