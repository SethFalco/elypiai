package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum StreamType {

    @SerializedName("live")
    LIVE("live"),

    @SerializedName("vodcast")
    VODCAST("vodcast");

    private final String NAME;

    StreamType(final String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public static StreamType get(String name) {
        for (StreamType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return null;
    }
}
