package com.elypia.elypiai.twitch.data;

public enum BroadcasterType {

    PARTNER("partner"),
    AFFILIATE("affiliate"),
    NORMAL("");

    private String apiName;

    BroadcasterType(String apiName) {
        this.apiName = apiName;
    }

    public static BroadcasterType getByName(String apiName) {
        for (BroadcasterType type : values()) {
            if (type.apiName.equals(apiName))
                return type;
        }

        return null;
    }
}
