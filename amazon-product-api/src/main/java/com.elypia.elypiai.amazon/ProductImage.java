package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.XmlElement;

public class ProductImage {

    @XmlElement(name = "URL", namespace = AmazonResult.NAMESPACE)
    private String url;

    @XmlElement(name = "Height", namespace = AmazonResult.NAMESPACE)
    private int height;

    @XmlElement(name = "Width", namespace = AmazonResult.NAMESPACE)
    private int width;

    public String getUrl() {
        return url;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
