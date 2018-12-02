package com.elypia.elypiai.runescape.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

/**
 * The RuneScape API returns dates in a format like: <strong>27-May-2018 20:40</strong>.
 * So we need a custom deserializer to parse this and convert the time to UTC.
 */
public class RuneScapeDateDeserializer implements JsonDeserializer<Date> {

    private final static SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm");

    static {
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive() || json.isJsonNull())
            throw new JsonParseException("Object is not a JSON primitive.");

        try {
            return format.parse(json.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
