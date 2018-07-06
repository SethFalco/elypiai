package com.elypia.elypiai.utils.gson.deserializers;

import com.google.gson.*;
import org.jsoup.Jsoup;

import java.lang.reflect.Type;

public class DocumentDeserializer implements JsonDeserializer<String> {

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Jsoup.parse(json.getAsString()).text();
    }
}
