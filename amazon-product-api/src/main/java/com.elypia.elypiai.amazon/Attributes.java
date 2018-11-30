package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.XmlElement;

public class Attributes {

    @XmlElement(name = "Brand", namespace = AmazonResult.NAMESPACE)
    private String brand;

    @XmlElement(name = "Color", namespace = AmazonResult.NAMESPACE)
    private String color;

    @XmlElement(name = "EAN", namespace = AmazonResult.NAMESPACE)
    private long ean;

    @XmlElement(name = "Manufacturer", namespace = AmazonResult.NAMESPACE)
    private String manufacturer;

    @XmlElement(name = "ProductGroup", namespace = AmazonResult.NAMESPACE)
    private String productGroup;

    @XmlElement(name = "Title", namespace = AmazonResult.NAMESPACE)
    private String title;

    @XmlElement(name = "ListPrice", namespace = AmazonResult.NAMESPACE)
    private Price price;

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public long getEan() {
        return ean;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public String getTitle() {
        return title;
    }

    public Price getPrice() {
        return price;
    }
}
