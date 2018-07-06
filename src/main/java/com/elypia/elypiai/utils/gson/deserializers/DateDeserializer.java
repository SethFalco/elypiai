package com.elypia.elypiai.utils.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class DateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonNull()) {
            String string = json.getAsString();
            long value = Long.parseLong(string);

            if (string.length() <= 7)
                value *= 1000;

            return new Date(value);
        }

        return null;
    }
}
