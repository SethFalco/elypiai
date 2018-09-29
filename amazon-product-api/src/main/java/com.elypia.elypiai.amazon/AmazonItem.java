package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.XmlElement;

public class AmazonItem {

    @XmlElement(name = "Title")
    private String title;

    /**
     * Amazon Standard Identification Number (ASIN)
     * A unique identifer used by Amazon to identify products
     * or product models / colors.
     */

    @XmlElement(name = "ASIN")
    private String asin;

    /**
     * The Amazon Standard Identifcation Number (ASIN) as a whole
     * product, regardless of model. Products with multiple versions
     * or colors will have the same parent ASIN.
     */

    @XmlElement(name = "ParentASIN")
    private String parentAsin;

    /**
     * A large copy of the default presented image.
     */

    @XmlElement(name = "LargeImage") // ? LargeImage.URL
    private String largeImage;

    /**
     * The price of the item.
     */

    @XmlElement(name = "Amount")
    private double price;

    /**
     * The currency this item is listed in.
     */

    @XmlElement(name = "CurrencyCode")
    private String currenyCode;

    @XmlElement(name = "FormattedPrice")
    private String formattedPrice;

    public String getTitle() {
        return title;
    }

    public String getAsin() {
        return asin;
    }

    public String getParentAsin() {
        return parentAsin;
    }

    public String getUrl() {
        return null;
//        return String.format("%sdp/%s?tag=%s", endpoint.getShoppingUrl(), asin, amazon.getId());
    }

    public String getImage() {
        return largeImage;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currenyCode;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }
}
