package com.elypia.elypiai.nanowrimo.deserializers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateDeserializer extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    static {
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public Date unmarshal(String v) throws Exception {
        return format.parse(v);
    }

    @Override
    public String marshal(Date v) {
        return format.format(v);
    }
}
