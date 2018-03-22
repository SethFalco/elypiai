package com.elypia.elypiai.sightengine.data;

public enum SightEngineErrorType {

    MEDIA_ERROR("media_error");

    private final String API_NAME;

    SightEngineErrorType(String apiName) {
        this.API_NAME = apiName;
    }

    public static SightEngineErrorType getByName(String apiName) {
        for (SightEngineErrorType type : values()) {
            if (type.API_NAME.equalsIgnoreCase(apiName))
                return type;
        }

        return null;
    }
}
