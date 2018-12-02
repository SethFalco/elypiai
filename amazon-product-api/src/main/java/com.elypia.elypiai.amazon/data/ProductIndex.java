package com.elypia.elypiai.amazon.data;

public enum ProductIndex {

    UNKNOWN(null),
    ALL("All"),
    APPLIANCES("Appliances"),
    ARTS_AND_CRAFTS("ArtsAndCrafts"),
    AUTOMOTIVES("Automotives"),
    BABY("Baby"),
    BEAUTY("Beauty"),
    BLENDED("Blended"),
    BOOKS("Books"),
    COLLECTIBLES("Collectibles"),
    ELECTRONICS("Electronics"),
    FASHION("Fashion"),
    FASHION_BABY("FashionBaby"),
    FASHION_BOYS("FashionBoys"),
    FASHION_GIRLS("FashionGirls"),
    FASHION_MEN("FashionMen"),
    FASHION_WOMEN("FashionWomen"),
    GIFT_CARDS("GiftCards"),
    GROCERY("Grocery"),
    HEALTH_PERSONAL_CARE("HealthPersonalCare"),
    HOME_GARDEN("HomeGarden"),
    INDUSTRIAL("Industrial"),
    KINDLE_STORE("KindleStore"),
    LAWN_AND_GARDEN("LawnAndGarden"),
    LUGGAGE("Luggage"),
    MP3_DOWNLOADS("MP3Downloads"),
    MAGAZINES("Magazines"),
    MERHCANTS("Merchants"),
    MOBILE_APPS("MobileApps"),
    MOVIES("Movies"),
    MUSIC("Music"),
    MUSICAL_INSTRUMENTS("MusicalInstruments"),
    OFFICE_PRODUCTS("OfficeProducts"),
    PC_HARDWARE("PCHardware"),
    PET_SUPPLIES("PetSupplies"),
    SOFTWARE("Software"),
    SPORTING_GOODS("SportingGoods"),
    TOOLS("Tools"),
    TOYS("Toys"),
    UNBOX_VIDEO("UnboxVideo"),
    VIDEO_GAMES("VideoGames"),
    WINE("Wine"),
    WIRELESS("Wireless");

    private final String NAME;

    ProductIndex(String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }
}
