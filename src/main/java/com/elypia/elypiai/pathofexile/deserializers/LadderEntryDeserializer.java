package com.elypia.elypiai.pathofexile.deserializers;

import com.elypia.elypiai.pathofexile.LadderEntry;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class LadderEntryDeserializer implements JsonDeserializer<List<LadderEntry>> {

    private static Gson gson = new Gson();

    @Override
    public List<LadderEntry> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array  = json.getAsJsonObject().getAsJsonArray("entries");
        return gson.fromJson(array, typeOfT);
    }
}
