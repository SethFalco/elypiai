package com.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

public enum AccountType {

    UNKNOWN(null),

    @SerializedName("staff")
    STAFF("staff"),

    @SerializedName("admin")
    ADMIN("admin"),

    @SerializedName("global_mod")
    GLOBAL_MOD("global_mod"),

    @SerializedName("")
    USER("");

    private final String API_NAME;

    AccountType(final String name) {
        API_NAME = name;
    }

    public String getName() {
        return API_NAME;
    }

    public static AccountType of(String name) {
        AccountType[] types = values();

        for (int i = 1; i < types.length; i++) {
            AccountType type = types[i];

            if (type.API_NAME.equals(name))
                return type;
        }

        return UNKNOWN;
    }
}
