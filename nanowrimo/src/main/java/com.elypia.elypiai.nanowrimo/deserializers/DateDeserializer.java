package com.elypia.elypiai.nanowrimo.deserializers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDeserializer extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String v) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(v);
    }

    @Override
    public String marshal(Date v) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").format(v);
    }
}
