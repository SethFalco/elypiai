package com.elypia.elypiai.amazon;

import com.elypia.elypiai.utils.JsoupUtils;
import org.jsoup.nodes.Element;

import java.util.Currency;

public class AmazonItem {

    private Amazon amazon;

    private String asin;
    private String parentAsin;
    private String url;
    private String largeImage;
    private double price;
    private Currency currency;

    public AmazonItem(Amazon amazon, Element element) {
        this.amazon = amazon;

        asin = JsoupUtils.getTextByTag(element, "ASIN");
        parentAsin = JsoupUtils.getTextByTag(element, "ParentASIN");
        url = amazon.getEndpoint().getProductUrl(asin);
        price = Double.parseDouble(JsoupUtils.optTextByTag(element, "Amount", 0)) / 100;
        currency = Currency.getInstance(JsoupUtils.getTextByTag(element, "CurrencyCode"));

        Element largeImageEle = JsoupUtils.getElementByTag(element, "LargeImage");
        largeImage = JsoupUtils.getTextByTag(largeImageEle, "URL");
    }

    public Amazon getAmazon() {
        return amazon;
    }

    public String getAsin() {
        return asin;
    }

    public String getParentAsin() {
        return parentAsin;
    }

    public String getUrl() {
        return url;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public double getPrice() {
        return price;
    }

    public String getPricePretty() {
        int decimalDigits = currency.getDefaultFractionDigits();
        String symbol = currency.getSymbol();

        return String.format("%s%,f." + decimalDigits, symbol, price);
    }

    public Currency getCurrency() {
        return currency;
    }
}
