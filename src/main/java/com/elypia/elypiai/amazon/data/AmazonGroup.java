package com.elypia.elypiai.amazon.data;

public enum AmazonGroup {

    ACCESSORIES("Accessories"),
    ALTERNATATE_VERSIONS("AlternateVersions"),
    BROWSER_NOTE_INFO("BrowserNodeInfo"),
    BROWSER_NODES("BrowserNodes"),
    CART("Cart"),
    CART_NEW_RELEASES("CartNewReleases"),
    CART_TOP_SELLERS("CartTopSellers"),
    CART_SIMILARITIES("CartSimilarites"),
    EDITORIAL_REVIEW("EditorialReview"),
    IMAGES("Images"),
    ITEM_ATTRIBUTES("ItemAttributes"),
    ITEM_IDS("ItemIds"),
    LARGE("Large"),
    MEDIUM("Medium"),
    MOST_GIFTED("MostGifted"),
    MOST_WISHED_FOR("MostWishedFor"),
    NEW_RELEASES("NewReleases"),
    OFFER_FULL("OfferFull"),
    OFFER_LISTINGS("OfferListings"),
    OFFERS("Offers"),
    OFFER_SUMMARY("OfferSummary"),
    PROMOTION_SUMMARY("PromotionSummary"),
    RELATED_ITEMS("RelatedItems"),
    REQUEST("Request"),
    REVIEW("Reviews"),
    SALES_RANK("SalesRank"),
    SEARCH_BIN("SearchBin"),
    SIMILARITIES("Similarities"),
    SMALL("Small"),
    TOP_SELLERS("TopSellers"),
    TRACKS("Tracks"),
    VARIATION("Variation"),
    VARIATION_IMAGES("VariationImages"),
    VARIATION_MATRIX("VariationMatrix"),
    VARIATION_OFFERS("VariationOffers"),
    VARIATION_SUMMARY("VariationSummary");

    private String apiName;

    AmazonGroup(String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

    @Override
    public String toString() {
        return apiName;
    }
}
