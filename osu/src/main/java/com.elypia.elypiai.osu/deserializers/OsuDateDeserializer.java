package com.elypia.elypiai.osu.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

public class OsuDateDeserializer implements JsonDeserializer<Date> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static {
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Object is not JSON primitive.");

        try {
            return format.parse(json.getAsString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
