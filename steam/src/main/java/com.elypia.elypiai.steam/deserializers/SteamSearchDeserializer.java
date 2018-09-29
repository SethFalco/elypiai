package com.elypia.elypiai.steam.deserializers;

import com.elypia.elypiai.steam.SteamSearch;
import com.google.gson.*;

import java.lang.reflect.Type;

public class SteamSearchDeserializer implements JsonDeserializer<SteamSearch> {

    private static Gson gson = new Gson();

    @Override
    public SteamSearch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject().getAsJsonObject("response");
        return gson.fromJson(object, typeOfT);
    }
}
