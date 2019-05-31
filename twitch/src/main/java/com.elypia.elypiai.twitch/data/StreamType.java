package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum StreamType {

    UNKNOWN(null),

    @SerializedName("live")
    LIVE("live"),

    @SerializedName("vodcast")
    VODCAST("vodcast");

    private final String API_NAME;

    StreamType(final String name) {
        API_NAME = name;
    }

    public String getName() {
        return API_NAME;
    }

    public static StreamType get(String apiName) {
        StreamType[] types = values();

        for (int i = 1; i < types.length; i++) {
            StreamType type = types[i];

            if (type.API_NAME.equals(apiName))
                return type;
        }

        return UNKNOWN;
    }
}
