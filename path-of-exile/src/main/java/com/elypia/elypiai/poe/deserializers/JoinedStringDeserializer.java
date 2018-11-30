package com.elypia.elypiai.poe.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.StringJoiner;

public class JoinedStringDeserializer implements JsonDeserializer<String> {

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        StringJoiner joiner = new StringJoiner("");
        array.forEach(o -> joiner.add(o.getAsString()));
        return joiner.toString();
    }
}
