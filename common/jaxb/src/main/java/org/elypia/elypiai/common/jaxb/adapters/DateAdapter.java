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

package org.elypia.elypiai.common.jaxb.adapters;

import org.slf4j.*;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.*;
import java.util.*;

/**
 * Convert date parse by the specified pattern or as unix time
 * if no format is specified.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateAdapter.class);

    private SimpleDateFormat format;

    /** Use unix time instead of a date format. */
    public DateAdapter() {
        this(null);
    }

    /**
     * @param formatString The date format, or null if unix time.
     */
    public DateAdapter(String formatString) {
        if (formatString == null)
            return;

        format = new SimpleDateFormat(formatString);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date unmarshal(String v) {
        Objects.requireNonNull(v);

        if (format == null)
            return new Date(Long.valueOf(v));

        try {
            return format.parse(v);
        } catch (ParseException ex) {
            logger.error("Unable to parse date string as Date.", ex);
        }

        return null;
    }

    @Override
    public String marshal(Date v) {
        return (format != null) ? format.format(v) : String.valueOf(v.getTime());
    }
}
