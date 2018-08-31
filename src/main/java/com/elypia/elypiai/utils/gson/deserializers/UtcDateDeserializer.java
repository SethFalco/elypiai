package com.elypia.elypiai.utils.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

public class UtcDateDeserializer implements JsonDeserializer<Date> {

    private final SimpleDateFormat FORMAT;

    public UtcDateDeserializer(String format) {
        FORMAT = new SimpleDateFormat(format);
        FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive())
            return null;

        String date = json.getAsString();

        try {
            return FORMAT.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
