package com.elypia.elypiai.utils;

import java.util.Objects;

import static com.elypia.elypiai.utils.Country.*;

public enum Language {

    AFRIKAANS("Afrikaans", "AF", SOUTH_AFRICA, NAMBIA),
    AMHARIC("Amharic", "AM", ETHIOPIA),
    ARABIC("Arabic", "AR"),
    AZERVAIKANI("Azervaikani", "AZ"),
    BERLARUSIAN("Berlarusian", "BE", BELARUS),
    BULGARIAN("Bulgarian", "BG", BULGARIA),
    BENGALI("Bengali", "BN", BENGLADESH),
    BOSNIAN("Bosnian", "BS", BOSNIA),
    CATALAN("Catalan", "CA"),
    CEBUANO("Cebuano", "CEB"),
    CORSICAN("Corsican", "CO"),
    CZECH("Czech", "CS", CZECH_REPUBLIC),
    WELSH("Welsh", "CY"),
    DANISH("Danish", "DA", DENMARK, FAROE_ISLANDS),
    GERMAN("German", "DE", GERMANY),
    GREEK("Greek", "EL", GREECE),
    ENGLISH("English", "EN", UNITED_STATES, UNITED_KINGDOM, AUSTRALIA, CANADA),
    ESPERANTO("Esperanto", "EO"),
    ESTONIAN("Estonian", "ET", ESTONIA),
    BASQUE("Basque", "EU"),
    PERSIAN("Persian", "FA", IRAN),
    FINNISH("Finnish", "FI", FINLAND),
    FRENCH("French", "FR", FRANCE),
    WESTERN_FRISTIAN("Western Fristian", "FY"),
    IRISH("Irish", "GA", IRELAND),
    GAELIC("Gaelic", "GD"),
    GALICAN("Galican", "GL"),
    GUJARATI("Gujarati", "GU"),
    HAUSA("Hausa", "HA", NIGER),
    HAWAIIAN("Hawaiian", "HAW"),
    HINDI("Hindi", "HI", INDIA),
    HMONG("Hmong", "HMN"),
    CROATION("Croation", "HR", CROATIA),
    HUNGARIAN("Hungarian", "HU", HUNGARY),
    ARMENIAN("Armenian", "HY", ARMENIA),
    INDONESIAN("Indonesian", "ID", INDONESIA),
    IGBO("Igbo", "IG", NIGERIA),
    ICELANDIC("Icelandic", "IS", ICELAND),
    ITALIAN("Italian", "IT", ITALY),
    JAPANESE("Japanese", "JA", JAPAN),
    GEORGIAN("Georgian", "KA", GEORGIA),
    KAZAKH("Kazakh", "KK", KAZAKHSTAN),
    CENTRAL_KHMER("Central Khmer", "KM", CAMBODIA),
    KOREAN("Korean", "KO", NORTH_KOREA, SOUTH_KOREA),
    KURDISH("Kurdish", "KU", IRAQ),
    LATIN("Latin", "LA"),
    LUXEMBOURGISH("Luxembourgish", "LB", LUXEMBOURG),
    LITHUANIAN("Lithuanian", "LT", LITHUANIA),
    MALAGASY("Malagasy", "MG", MADAGASCAR),
    MAORI("Maori", "MI", NEW_ZEALAND),
    NEPALI("Nepali", "NE", NEPAL),
    DUTCH("Dutch", "NL", NETHERLANDS, SINT_MAARTEN, SURINAME, CURACAO, BELGIUM, ARUBA),
    NORWEGIAN("Norwegian", "NO", NORWAY),
    CHICHEWA("Chichewa", "NY", MALAWI),
    PANJABI("Panjabi", "PA"),
    POLISH("Polish", "PL", POLAND),
    PASHTO("Pashto", "PS", AFGHANISTAN),
    ROMANIAN("Romanian", "RO", ROMANIA),
    RUSSIAN("Russian", "RU", RUSSIA, KYRGYZSTAN, MOLDOVA),
    SINHALA("Sinhala", "SI", SRI_LANKA),
    SLOVAK("Slovak", "SK", SLOVAKIA),
    SLOVENIAN("Slovenian", "SL", SLOVENIA),
    SAMOAN("Samoan", "SM", SAOMA),
    SHONA("Shona", "SN", ZIMBABWE),
    SOMALI("Somali", "SO", SOMALIA),
    ALBANIAN("Albanian", "SQ", ALBANIA),
    SERBIAN("Serbian", "SR", SERBIA),
    SUNDANESE("Sundanese", "SU", SUDAN),
    SWEDISH("Swedish", "SV", SWEDEN),
    SWAHILI("Swahili", "SW", TANZANIA, KENYA, DEMOCRATIC_REPUBLIC_OF_THE_CONGO, RWANDA, UGANDA),
    TAMIL("Tamil", "TA"),
    TELUGU("Telugu", "TE"),
    TAJIK("Tajik", "TG", TAJIKISTAN),
    TAGALOG("Tagalog", "TL", PHILIPPINES),
    TURKISH("Turkish", "TR", TURKEY, CYPRUS),
    UKRAINIAN("Ukrainian", "UK", UKRAINE),
    URDU("Urdu", "UR", PAKISTAN),
    UZBEK("uzbek", "UZ", UZBEKISTAN),
    VIETNAMESE("Vietnamese", "VI", VIETNAM),
    XHOSA("Xhosa", "XH"),
    YIDDISH("Yiddish", "YI"),
    YARUBA("Yaruba", "YO"),
    CHINESE("Chinese", "ZH", CHINA),
    ZULU("Zulu", "ZU");

    private final String LANGUAGE;
    private final String CODE;
    private final Country[] COUNTRIES;

    Language(String language, String code, Country... countries) {
        LANGUAGE = language;
        CODE = code;
        COUNTRIES = countries;
    }

    public String getLanguageName() {
        return LANGUAGE;
    }

    public String getCode() {
        return CODE;
    }

    public Country[] getCountries() {
        return COUNTRIES;
    }

    public static Language getByCode(String code) {
        Objects.requireNonNull(code);

        for (Language language : values()) {
            if (language.CODE.equalsIgnoreCase(code))
                return language;
        }

        return null;
    }
}
