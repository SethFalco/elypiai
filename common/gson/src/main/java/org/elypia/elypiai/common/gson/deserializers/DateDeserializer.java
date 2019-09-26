/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.common.gson.deserializers;

import com.google.gson.*;
import org.slf4j.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

/**
 * Convert date parse by the specified pattern or as unix time
 * if no format is specified.
 *
 * @author seth@elypia.org (Syed Shah)
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
