package com.elypia.elypiai.runescape.data;

import com.google.gson.annotations.SerializedName;

public enum RuneScapeError {

    /**
     * RuneScape players that have their account privacy settings
     * set to private.
     */

    @SerializedName("PROFILE_PRIVATE")
    PROFILE_PRIVATE,

    /**
     * No account exists with that username.
     */

    @SerializedName("NO_PROFILE")
    NO_PROFILE,

    /**
     * Accounts that are no longer active. Potentially
     * permanently banned accounts.
     */

    @SerializedName("NOT_A_MEMBER")
    NOT_A_MEMBER
}
