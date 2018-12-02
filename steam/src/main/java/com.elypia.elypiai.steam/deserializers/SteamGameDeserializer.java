package com.elypia.elypiai.steam.deserializers;

import com.elypia.elypiai.steam.SteamGame;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class SteamGameDeserializer implements JsonDeserializer<List<SteamGame>> {

    @Override
    public List<SteamGame> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement games = json.getAsJsonObject().getAsJsonObject("response").getAsJsonArray("games");
        return new Gson().fromJson(games, typeOfT);
    }
}
