package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.XmlElement;

public class Price {

    @XmlElement(name = "Amount", namespace = AmazonResult.NAMESPACE)
    private int amount;

    @XmlElement(name = "CurrencyCode", namespace = AmazonResult.NAMESPACE)
    private String currencyCode;

    @XmlElement(name = "FormattedPrice", namespace = AmazonResult.NAMESPACE)
    private String formattedPrice;

    public int getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }
}
