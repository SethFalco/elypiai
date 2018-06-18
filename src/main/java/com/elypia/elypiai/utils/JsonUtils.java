package com.elypia.elypiai.utils;

import com.google.gson.*;

public final class JsonUtils {

    private static JsonParser parser = new JsonParser();

    private JsonUtils() {

    }

    public static JsonObject toJsonObject(String body) {
        return parser.parse(body).getAsJsonObject();
    }

    public static JsonArray toJsonArray(String body) {
        return parser.parse(body).getAsJsonArray();
    }
}
