package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

public enum MonsterType {

    UNKNOWN("Unknown"),

    @SerializedName("Normal")
    NORMAL("Normal"),

    @SerializedName("Effect")
    EFFECT("Effect"),

    @SerializedName("Fusion")
    FUSION("Fusion"),

    @SerializedName("Ritual")
    RITUAL("Ritual"),

    @SerializedName("Tuner")
    TUNER("Tuner"),

    @SerializedName("Synchro")
    SYNCHRO("Synchro"),

    @SerializedName("Flip")
    FLIP("Flip");

    private final String NAME;

    MonsterType(final String name) {
        NAME = name;
    }

    public static MonsterType get(String name) {
        for (MonsterType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return UNKNOWN;
    }
}
