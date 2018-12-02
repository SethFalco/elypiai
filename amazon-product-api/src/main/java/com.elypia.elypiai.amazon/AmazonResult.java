package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(
    name = "ItemSearchResponse",
    namespace = AmazonResult.NAMESPACE
)
public class AmazonResult {

    protected static final String NAMESPACE = "http://webservices.amazon.com/AWSECommerceService/2011-08-01";

    @XmlElementWrapper(name = "Items", namespace = NAMESPACE)
    @XmlElement(name = "Item", namespace = NAMESPACE)
    private List<Product> items;

    public List<Product> getItems() {
        return items;
    }
}
