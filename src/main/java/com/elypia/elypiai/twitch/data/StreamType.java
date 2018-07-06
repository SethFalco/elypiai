package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum StreamType {

    @SerializedName("live")
    LIVE,

    @SerializedName("vodcast")
    VODCAST
}
