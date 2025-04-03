/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package org.elypia.elypiai.steam.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.steam.Steam;
import org.elypia.retropia.gson.deserializers.BitBooleanDeserializer;
import org.elypia.retropia.gson.deserializers.UnixSecondsInstantDeserializer;

import java.time.Instant;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class SteamUser {

    private Steam steam;

    /**
     * 64bit SteamID of the user.
     */
    @SerializedName("steamid")
    private long id;

    /**
     * Player's persona name (display name).
     */
    @SerializedName("personaname")
    private String username;

    /**
     * Full URL of the player's Steam Community profile.
     */
    @SerializedName("profileurl")
    private String url;

    @SerializedName("avatar")
    private String avatarLow;

    /**
     * Full URL of the player's 184x184px avatar. If the user hasn't configured
     * an avatar, this will be the default ? avatar.
     */
    @SerializedName("avatarmedium")
    private String avatarMedium;

    @SerializedName("avatarfull")
    private String avatarHigh;

    /**
     * User's current status. If the player's profile is private,
     * this will always be "0".
     */
    @SerializedName("personastate")
    private PersonaState state;

    /**
     * Whether the profile is visible or not.
     */
    @SerializedName("communityvisibilitystate")
    private CommunityVisibilityState communityVisibilityState;

    /**
     * Indicates the user has a community profile configured.
     */
    @SerializedName("profilestate")
    @JsonAdapter(BitBooleanDeserializer.class)
    private boolean hasProfile;

    /**
     * Last time the user was online.
     */
    @SerializedName("lastlogoff")
    @JsonAdapter(UnixSecondsInstantDeserializer.class)
    private Instant lastLogOff;

    /**
     * Indicates the profile allows public comments.
     */
    @SerializedName("commentpermission")
    @JsonAdapter(BitBooleanDeserializer.class)
    private boolean canComment;

    /**
     * Player's "Real Name", if they have set it, or {@code null} if the profile
     * is private.
     */
    @SerializedName("realname")
    private String realName;

    /**
     * Player's primary group, as configured in their Steam Community profile,
     * or {@code null} if the profile is private.
     */
    @SerializedName("primaryclanid")
    private long primaryClan;

    /**
     * The time the player's account was created, or {@code null} if the profile
     * is private.
     */
    @SerializedName("timecreated")
    @JsonAdapter(UnixSecondsInstantDeserializer.class)
    private Instant timeCreated;

    /**
     * If set on the user's Steam Community profile, the user's country of
     * residence, 2-character ISO country code.
     */
    @SerializedName("loccountrycode")
    private String countryCode;

    /**
     * If set on the user's Steam Community profile, The user's state of
     * residence.
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
     * @return
     *     ID of the user. See {@link #getUsername()} for name of the user
     *     instead.
     */
    public long getId() {
        return id;
    }

    /**
     * @return Name of the user as displayed on their Steam profile.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return    Return the Url to their steam profile.
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
     * @return
     *     Full URL of the player's 184x184px avatar. If the user hasn't
     *     configured an avatar, this will be the default ? avatar.
     */
    public String getAvatarHigh() {
        return avatarHigh;
    }

    /**
     * @return
     *     Users personal state/status.
     * @see PersonaState
     */
    public PersonaState getPersonaState() {
        return state;
    }

    /**
     * @return Users community visible state.
     */
    public CommunityVisibilityState getCommunityVisibilityState() {
        return communityVisibilityState;
    }

    public boolean hasProfile() {
        return hasProfile;
    }

    public Instant getLastLogOff() {
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

    public Instant getTimeCreated() {
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
