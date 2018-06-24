package com.elypia.elypiai.utils.okhttp.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StringDeserializer implements JsonDeserializer<String> {

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
