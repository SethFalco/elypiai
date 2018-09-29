package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum BroadcasterType {

    @SerializedName("partner")
    PARTNER,

    @SerializedName("affiliate")
    AFFILIATE,

    @SerializedName("")
    NORMAL
}
