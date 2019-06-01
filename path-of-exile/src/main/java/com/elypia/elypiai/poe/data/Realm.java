package com.elypia.elypiai.poe.data;

import com.google.gson.annotations.SerializedName;

public enum Realm {

    UNKNOWN("Unknown"),

    @SerializedName("pc")
    PC("PC"),

    @SerializedName("xbox")
    XBOX("Xbox"),

    @SerializedName("sony")
    SONY("Sony");

    private final String NAME;

    Realm(final String NAME) {
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }

    public static Realm get(String name) {
        for (Realm realm : values()) {
            if (realm.NAME.equals(name))
                return realm;
        }

        return UNKNOWN;
    }
}
