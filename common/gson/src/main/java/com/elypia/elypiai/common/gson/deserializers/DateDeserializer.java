package com.elypia.elypiai.common.gson.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Convert date parse by the specified pattern or as unix time
 * if no format is specified.
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateDeserializer.class);

    private SimpleDateFormat format;

    /** Use unix time instead of a date format. */
    public DateDeserializer() {
        this(null);
    }

    /**
     * @param formatString The date format, or null if unix time.
     */
    public DateDeserializer(String formatString) {
        if (formatString == null)
            return;

        format = new SimpleDateFormat(formatString);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive() || json.isJsonNull())
            throw new JsonParseException("Object is not a JSON primitive.");

        if (format == null)
            return new Date(json.getAsLong());

        try {
            return format.parse(json.getAsString());
        } catch (ParseException ex) {
            logger.error("Unable to parse date string as Date.", ex);
        }

        return null;
    }
}
