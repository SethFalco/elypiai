package com.elypia.elypiai.runescape.deserializers;

import com.elypia.elypiai.runescape.QuestStats;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class QuestStatDeserializer implements JsonDeserializer<List<QuestStats>> {

    private static final Gson GSON = new Gson();

    @Override
    public List<QuestStats> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject().getAsJsonArray("quests");
        List<QuestStats> list = GSON.fromJson(element, typeOfT);
        return list.isEmpty() ?  null : list;
    }
}
