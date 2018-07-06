package com.elypia.elypiai.sightengine.deserializers;

import com.elypia.elypiai.sightengine.NudityScore;
import com.google.gson.*;

import java.lang.reflect.Type;

public class NudityScoreDeserializer implements JsonDeserializer<NudityScore> {

    private static Gson gson = new Gson();

    @Override
    public NudityScore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject element = json.getAsJsonObject().getAsJsonObject("nudity");
        return gson.fromJson(element, typeOfT);
    }
}
