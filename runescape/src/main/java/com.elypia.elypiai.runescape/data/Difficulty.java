package com.elypia.elypiai.runescape.data;

import com.google.gson.annotations.SerializedName;

public enum Difficulty {

    @SerializedName("0")
    NOVICE(0),

    @SerializedName("1")
    INTERMEDIATE(1),

    @SerializedName("2")
    EXPERIENCED(2),

    @SerializedName("3")
    MASTER(3),

    @SerializedName("4")
    GRANDMASTER(4),

    @SerializedName("250")
    SPECIAL(250);

    private final int ID;

    Difficulty(int id) {
        ID = id;
    }

    public int getId() {
        return ID;
    }
}
