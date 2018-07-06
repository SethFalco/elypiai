package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum OsuTeamType {

    @SerializedName("0")
    HEAD_TO_HEAD,

    @SerializedName("1")
    TAG_COOP,

    @SerializedName("2")
    TEAM_VS,

    @SerializedName("3")
    TAG_TEAM_VS
}