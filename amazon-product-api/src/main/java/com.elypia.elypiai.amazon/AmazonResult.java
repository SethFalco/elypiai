package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ItemSearchResponse")
public class AmazonResult {

    @XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
    private List<AmazonItem> items;

    public List<AmazonItem> getItems() {
        return items;
    }
}
