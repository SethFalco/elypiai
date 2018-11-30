package com.elypia.elypiai.poe.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class StashItemNameDeserializer implements JsonDeserializer<String> {

    private static Pattern STRIPPER = Pattern.compile("^(?:<<set:[MS]+>>)+");

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return STRIPPER.matcher(json.getAsString()).replaceAll("");
    }
}
