package com.elypia.elypiai.deviantart;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "item", namespace = "")
public class Deviation {

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "link")
    private String url;

    @XmlElement(name = "pubDate")
    private Date publicationDate;

    @XmlElement(name = "rating", namespace = "media")
    private String rating;

    @XmlElement(name = "category")
    private String categories;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getRating() {
        return rating;
    }

    public String getCategories() {
        return categories;
    }
}
