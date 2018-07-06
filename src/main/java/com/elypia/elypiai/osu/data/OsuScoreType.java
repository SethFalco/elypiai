package com.elypia.elypiai.osu.data;

import com.google.gson.annotations.SerializedName;

public enum OsuScoreType {

    @SerializedName("0")
    SCORE,

    @SerializedName("1")
    ACCURACY,

    @SerializedName("2")
    COMBO,

    @SerializedName("3")
    SCORE_V2
}