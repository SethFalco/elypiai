package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.*;
import com.elypia.elypiai.utils.gson.deserializers.UtcDateDeserializer;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class OsuMatchDeserializer implements JsonDeserializer<OsuMatch> {

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateDeserializer(Osu.OSU_DATE_FORMAT)).create();

    @Override
    public OsuMatch deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonObject match = object.getAsJsonObject("match");
        match.add("games", object.getAsJsonArray("games"));
        return GSON.fromJson(match, typeOfT);
    }
}
