package com.elypia.elypiai.common.jaxb.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Convert date parse by the specified pattern or as unix time
 * if no format is specified.
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
