package com.elypia.elypiai.poe.deserializers;

import com.elypia.elypiai.poe.LadderEntry;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class LadderEntryDeserializer implements JsonDeserializer<List<LadderEntry>> {

    private final Gson gson;

    public LadderEntryDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<LadderEntry> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array  = json.getAsJsonObject().getAsJsonArray("entries");
        return gson.fromJson(array, typeOfT);
    }
}
