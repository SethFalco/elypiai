package com.elypia.elypiai.common.gson.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

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
