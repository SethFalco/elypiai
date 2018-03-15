package com.elypia.elypiai.myanimelist.data;

public enum AnimeType {

    TV("TV"),
    MOVIE("Movie"),
    SPECIAL("Special");

    private String apiName;

    AnimeType(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

    public static AnimeType getTypeByName(String name) {
        for (AnimeType type : values()) {
            if (type.getApiName().equals(name))
                return type;
        }

        return null;
    }
}
