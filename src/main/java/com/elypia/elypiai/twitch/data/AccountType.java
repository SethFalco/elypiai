package com.elypia.elypiai.twitch.data;

public enum AccountType {

    STAFF("staff"),
    ADMIN("admin"),
    GLOBAL_MOD("global_mod"),
    USER("");

    private String apiName;

    AccountType(String apiName) {
        this.apiName = apiName;
    }

    public static AccountType getByName(String apiName) {
        for (AccountType type : values()) {
            if (type.apiName.equals(apiName))
                return type;
        }

        return null;
    }
}
