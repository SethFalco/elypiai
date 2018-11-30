package com.elypia.elypiai.twitch.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;

public class EmptyNullDeserializer implements JsonDeserializer<String> {

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonNull()) {
            String string = json.getAsString();

            if (!string.isEmpty())
                return string;
        }

        return null;
    }
}
