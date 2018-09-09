package com.elypia.elypiai.utils.gson.deserializers;

import com.elypia.elypiai.utils.DateFormat;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

public class UtcDateDeserializer implements JsonDeserializer<Date> {

    private SimpleDateFormat format;

    public UtcDateDeserializer() {

    }

    public UtcDateDeserializer(String dateFormat) {
        Objects.requireNonNull(dateFormat);

        format = new SimpleDateFormat(dateFormat);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Object is not JSON primitive.");

        String date = json.getAsString();

        if (format == null) {
            for (DateFormat dateFormat : DateFormat.values()) {
                if (dateFormat.getPattern().matcher(date).matches()) {
                    format = new SimpleDateFormat(dateFormat.getFormat());
                    format.setTimeZone(TimeZone.getTimeZone("UTC"));
                    break;
                }
            }
        }

        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
