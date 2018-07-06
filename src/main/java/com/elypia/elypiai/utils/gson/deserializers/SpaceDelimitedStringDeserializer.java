package com.elypia.elypiai.utils.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;

public class SpaceDelimitedStringDeserializer implements JsonDeserializer<List<String>> {

    private final Pattern SPLITTER = Pattern.compile("\\s+");

    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonNull()) {
            String string = json.getAsString();

            if (string.isEmpty())
                return Collections.emptyList();

            return Arrays.asList(SPLITTER.split(string));
        }

        return null;
    }
}
