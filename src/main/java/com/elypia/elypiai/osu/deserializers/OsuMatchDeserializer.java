package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class OsuMatchDeserializer implements JsonDeserializer<OsuMatch> {

    private static Gson gson = new GsonBuilder().setDateFormat(Osu.OSU_DATE_FORMAT).create();

    @Override
    public OsuMatch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonObject match = object.getAsJsonObject("match");
        match.add("games", object.getAsJsonArray("games"));
        return gson.fromJson(match, typeOfT);
    }
}
