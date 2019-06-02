package com.elypia.elypiai.nanowrimo;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class WordCountEntry {

    @XmlElement(name = "wc")
    private int wordcount;

    @XmlElement(name = "wcdate")
    private Date date;

    public int getWordcount() {
        return wordcount;
    }

    public Date getDate() {
        return date;
    }
}
