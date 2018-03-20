package com.elypia.elypiai.twitch.data;

public enum StreamType {

    LIVE("live"),
    VODCAST("vodcast");

    private String apiName;

    StreamType(String apiName) {
        this.apiName = apiName;
    }

    public static StreamType getByName(String apiName) {
        for (StreamType type : values()) {
            if (type.apiName.equals(apiName))
                return type;
        }

        return null;
    }
}
