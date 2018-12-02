package com.elypia.elypiai.osu.data;

public enum OsuId {

    USER_ID("id"),
    USERNAME("string");

    private final String TYPE;

    OsuId(String type) {
        TYPE = type;
    }

    public String getType() {
        return TYPE;
    }
}
