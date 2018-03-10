package com.elypia.elypiai.amazon;

import com.elypia.elypiai.utils.JsoupUtils;
import org.jsoup.nodes.Element;

import java.util.Currency;

public class AmazonItem {

    /**
     * Parent Amazon object that produced this item.
     */

    private Amazon amazon;

    /**
     * Amazon Standard Identification Number (ASIN)
     * A unique identifer used by Amazon to identify products
     * or product models / colors.
     */

    private String asin;

    /**
     * The Amazon Standard Identifcation Number (ASIN) as a whole
     * product, regardless of model. Products with multiple versions
     * or colors will have the same parent ASIN.
     */

    private String parentAsin;

    /**
     * The product URL of the item.
     */

    private String url;

    /**
     * A large copy of the default presented image.
     */

    private String largeImage;

    /**
     * The price of the item.
     */

    private double price;

    /**
     * The currency this item is listed in.
     */

    private Currency currency;

    /**
     * @param amazon The parent Amazon item.
     * @param element Raw format of an item for parsing.
     */

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

    /**
     * @return Get the perant Amazon instance that produced this item.
     */

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
