package com.elypia.elypiai.osu.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class OsuEventDisplayDeseralizer implements JsonDeserializer<String> {

    private static final Pattern stripper = Pattern.compile("<.+?>");

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String html = json.getAsString();
        return stripper.matcher(html).replaceAll("").trim();
    }
}
