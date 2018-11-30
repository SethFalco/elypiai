package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum BroadcasterType {

    @SerializedName("partner")
    PARTNER("partner"),

    @SerializedName("affiliate")
    AFFILIATE("affiliate"),

    @SerializedName("")
    NORMAL("");

    private final String NAME;

    BroadcasterType(final String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public static BroadcasterType get(String name) {
        for (BroadcasterType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return null;
    }
}
