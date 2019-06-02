package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum BroadcasterType {

    UNKNOWN(null),

    @SerializedName("partner")
    PARTNER("partner"),

    @SerializedName("affiliate")
    AFFILIATE("affiliate"),

    @SerializedName("")
    NORMAL("");

    private final String API_NAME;

    BroadcasterType(final String name) {
        API_NAME = name;
    }

    public String getName() {
        return API_NAME;
    }

    public static BroadcasterType get(String name) {
        BroadcasterType[] types = values();

        for (int i = 1; i < types.length; i++) {
            BroadcasterType type = types[i];

            if (type.API_NAME.equals(name))
                return type;
        }

        return UNKNOWN;
    }
}
