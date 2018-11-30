package com.elypia.elypiai.runescape.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CommaIntergerDeserializer implements JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonNull()) {
            String string = json.getAsString();

            if (!string.isEmpty())
                return Integer.valueOf(string.replace(",", ""));
        }

        return 0;
    }
}
