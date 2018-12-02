package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum AccountType {

    @SerializedName("staff")
    STAFF("staff"),

    @SerializedName("admin")
    ADMIN("admin"),

    @SerializedName("global_mod")
    GLOBAL_MOD("global_mod"),

    @SerializedName("")
    USER("");

    private final String NAME;

    AccountType(final String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public static AccountType get(String name) {
        for (AccountType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return null;
    }
}
