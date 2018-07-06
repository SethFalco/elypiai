package com.elypia.elypiai.runescape.deserializers;

import com.elypia.elypiai.runescape.QuestStats;
import com.google.gson.*;

import java.lang.reflect.Type;

public class QuestStatDeserializer implements JsonDeserializer<QuestStats> {

    @Override
    public QuestStats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement element = json.getAsJsonObject().getAsJsonArray("quests");
        return new Gson().fromJson(element, QuestStats.class);
    }
}
