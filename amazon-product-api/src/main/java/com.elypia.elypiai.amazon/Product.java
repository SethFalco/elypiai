package com.elypia.elypiai.amazon;

import javax.xml.bind.annotation.XmlElement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Product {

    private static final Pattern pattern = Pattern.compile("(?i)https?://(?:www\\.)?(?<url>amazon\\.[A-Z.]+/).+(?<tag>tag=[^&]+)");

    /**
     * Amazon Standard Identification Number (ASIN)
     * A unique identifer used by Amazon to identify products
     * or product models / colors.
     */
    @XmlElement(name = "ASIN", namespace = AmazonResult.NAMESPACE)
    private String asin;

    /**
     * The Amazon Standard Identifcation Number (ASIN) as a whole
     * product, regardless of model. Products with multiple versions
     * or colors will have the same parent ASIN.
     */
    @XmlElement(name = "ParentASIN", namespace = AmazonResult.NAMESPACE)
    private String parentAsin;

    /**
     * The URL to this product on Amazon, this URL includes
     * lots of URL parameters including the {@link Amazon#getSecret()}
     * so it is not safe to send to a user.
     */
    @XmlElement(name = "DetailPageURL", namespace = AmazonResult.NAMESPACE)
    private String detailsUrl;

    @XmlElement(name = "ItemAttributes", namespace = AmazonResult.NAMESPACE)
    private Attributes attributes;

    @XmlElement(name = "SmallImage", namespace = AmazonResult.NAMESPACE)
    private ProductImage smallImage;

    @XmlElement(name = "MediumImage", namespace = AmazonResult.NAMESPACE)
    private ProductImage medimumImage;

    @XmlElement(name = "LargeImage", namespace = AmazonResult.NAMESPACE)
    private ProductImage largeImage;

    public String getAsin() {
        return asin;
    }

    public String getParentAsin() {
        return parentAsin;
    }

    public String getUrl() {
        return detailsUrl;
    }

    /**
     * @return The URL to the detail page similarly to
     * {@link #getUrl()} but with extra URL paramters stripped off
     * including the {@link Amazon#getSecret()}.
     */
    public String getCleanUrl() {
        Matcher matcher = pattern.matcher(detailsUrl);

        if (matcher.find()) {
            String url = matcher.group("url");
            String tag = matcher.group("tag");
            return String.format("https://%sdp/%s?%s", url, asin, tag);
        }

        throw new IllegalStateException("Internal error occured, there's a problem with the regex.");
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public ProductImage getSmallImage() {
        return smallImage;
    }

    public ProductImage getMedimumImage() {
        return medimumImage;
    }

    public ProductImage getLargeImage() {
        return largeImage;
    }
}
