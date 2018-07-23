package com.elypia.elypiai.utils;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import static com.elypia.elypiai.utils.Country.*;

public enum Language {

    @SerializedName("af")
    AFRIKAANS("Afrikaans", "AF", SOUTH_AFRICA, NAMBIA),

    @SerializedName("am")
    AMHARIC("Amharic", "AM", ETHIOPIA),

    @SerializedName("ar")
    ARABIC("Arabic", "AR"),

    @SerializedName("az")
    AZERVAIKANI("Azervaikani", "AZ"),

    @SerializedName("be")
    BERLARUSIAN("Berlarusian", "BE", BELARUS),

    @SerializedName("bg")
    BULGARIAN("Bulgarian", "BG", BULGARIA),

    @SerializedName("bn")
    BENGALI("Bengali", "BN", BENGLADESH),

    @SerializedName("bs")
    BOSNIAN("Bosnian", "BS", BOSNIA),

    @SerializedName("ca")
    CATALAN("Catalan", "CA"),

    @SerializedName("ceb")
    CEBUANO("Cebuano", "CEB"),

    @SerializedName("co")
    CORSICAN("Corsican", "CO"),

    @SerializedName("cs")
    CZECH("Czech", "CS", CZECH_REPUBLIC),

    @SerializedName("cy")
    WELSH("Welsh", "CY"),

    @SerializedName(value = "da", alternate = "dk")
    DANISH("Danish", "DA", DENMARK, FAROE_ISLANDS),

    @SerializedName("de")
    GERMAN("German", "DE", GERMANY),

    @SerializedName("el")
    GREEK("Greek", "EL", GREECE),

    @SerializedName(value = "en", alternate = {"EN", "en-gb"})
    ENGLISH("English", "EN", UNITED_STATES, UNITED_KINGDOM, AUSTRALIA, CANADA),

    @SerializedName("eo")
    ESPERANTO("Esperanto", "EO"),

    @SerializedName("et")
    ESTONIAN("Estonian", "ET", ESTONIA),

    @SerializedName("eu")
    BASQUE("Basque", "EU"),

    @SerializedName("fa")
    PERSIAN("Persian", "FA", IRAN),

    @SerializedName("fi")
    FINNISH("Finnish", "FI", FINLAND),

    @SerializedName("fr")
    FRENCH("French", "FR", FRANCE),

    @SerializedName("fy")
    WESTERN_FRISTIAN("Western Fristian", "FY"),

    @SerializedName("ga")
    IRISH("Irish", "GA", IRELAND),

    @SerializedName("gd")
    GAELIC("Gaelic", "GD"),

    @SerializedName("gl")
    GALICAN("Galican", "GL"),

    @SerializedName("gu")
    GUJARATI("Gujarati", "GU"),

    @SerializedName("ha")
    HAUSA("Hausa", "HA", NIGER),

    @SerializedName("haw")
    HAWAIIAN("Hawaiian", "HAW"),

    @SerializedName("hi")
    HINDI("Hindi", "HI", INDIA),

    @SerializedName("hmn")
    HMONG("Hmong", "HMN"),

    @SerializedName("hr")
    CROATION("Croation", "HR", CROATIA),

    @SerializedName("hu")
    HUNGARIAN("Hungarian", "HU", HUNGARY),

    @SerializedName("hy")
    ARMENIAN("Armenian", "HY", ARMENIA),

    @SerializedName("id")
    INDONESIAN("Indonesian", "ID", INDONESIA),

    @SerializedName("ig")
    IGBO("Igbo", "IG", NIGERIA),

    @SerializedName("is")
    ICELANDIC("Icelandic", "IS", ICELAND),

    @SerializedName("it")
    ITALIAN("Italian", "IT", ITALY),

    @SerializedName("ja")
    JAPANESE("Japanese", "JA", JAPAN),

    @SerializedName("ka")
    GEORGIAN("Georgian", "KA", GEORGIA),

    @SerializedName("kk")
    KAZAKH("Kazakh", "KK", KAZAKHSTAN),

    @SerializedName("km")
    CENTRAL_KHMER("Central Khmer", "KM", CAMBODIA),

    @SerializedName("ko")
    KOREAN("Korean", "KO", NORTH_KOREA, SOUTH_KOREA),

    @SerializedName("ku")
    KURDISH("Kurdish", "KU", IRAQ),

    @SerializedName("la")
    LATIN("Latin", "LA"),

    @SerializedName("lb")
    LUXEMBOURGISH("Luxembourgish", "LB", LUXEMBOURG),

    @SerializedName("lt")
    LITHUANIAN("Lithuanian", "LT", LITHUANIA),

    @SerializedName("mg")
    MALAGASY("Malagasy", "MG", MADAGASCAR),

    @SerializedName("mi")
    MAORI("Maori", "MI", NEW_ZEALAND),

    @SerializedName("ne")
    NEPALI("Nepali", "NE", NEPAL),

    @SerializedName("nl")
    DUTCH("Dutch", "NL", NETHERLANDS, SINT_MAARTEN, SURINAME, CURACAO, BELGIUM, ARUBA),

    @SerializedName("no")
    NORWEGIAN("Norwegian", "NO", NORWAY),

    @SerializedName("ny")
    CHICHEWA("Chichewa", "NY", MALAWI),

    @SerializedName("pa")
    PANJABI("Panjabi", "PA"),

    @SerializedName("pl")
    POLISH("Polish", "PL", POLAND),

    @SerializedName("ps")
    PASHTO("Pashto", "PS", AFGHANISTAN),

    @SerializedName("ro")
    ROMANIAN("Romanian", "RO", ROMANIA),

    @SerializedName("ru")
    RUSSIAN("Russian", "RU", RUSSIA, KYRGYZSTAN, MOLDOVA),

    @SerializedName("si")
    SINHALA("Sinhala", "SI", SRI_LANKA),

    @SerializedName("sk")
    SLOVAK("Slovak", "SK", SLOVAKIA),

    @SerializedName("sl")
    SLOVENIAN("Slovenian", "SL", SLOVENIA),

    @SerializedName("sm")
    SAMOAN("Samoan", "SM", SAOMA),

    @SerializedName("sn")
    SHONA("Shona", "SN", ZIMBABWE),

    @SerializedName("so")
    SOMALI("Somali", "SO", SOMALIA),

    @SerializedName("sq")
    ALBANIAN("Albanian", "SQ", ALBANIA),

    @SerializedName("sr")
    SERBIAN("Serbian", "SR", SERBIA),

    @SerializedName("su")
    SUNDANESE("Sundanese", "SU", SUDAN),

    @SerializedName("sv")
    SWEDISH("Swedish", "SV", SWEDEN),

    @SerializedName("sw")
    SWAHILI("Swahili", "SW", TANZANIA, KENYA, DEMOCRATIC_REPUBLIC_OF_THE_CONGO, RWANDA, UGANDA),

    @SerializedName("ta")
    TAMIL("Tamil", "TA"),

    @SerializedName("te")
    TELUGU("Telugu", "TE"),

    @SerializedName("tg")
    TAJIK("Tajik", "TG", TAJIKISTAN),

    @SerializedName("tl")
    TAGALOG("Tagalog", "TL", PHILIPPINES),

    @SerializedName("tr")
    TURKISH("Turkish", "TR", TURKEY, CYPRUS),

    @SerializedName("uk")
    UKRAINIAN("Ukrainian", "UK", UKRAINE),

    @SerializedName("ur")
    URDU("Urdu", "UR", PAKISTAN),

    @SerializedName("uz")
    UZBEK("uzbek", "UZ", UZBEKISTAN),

    @SerializedName("vi")
    VIETNAMESE("Vietnamese", "VI", VIETNAM),

    @SerializedName("xh")
    XHOSA("Xhosa", "XH"),

    @SerializedName("yi")
    YIDDISH("Yiddish", "YI"),

    @SerializedName("yo")
    YARUBA("Yaruba", "YO"),

    @SerializedName("zh")
    CHINESE("Chinese", "ZH", CHINA),

    @SerializedName("zu")
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
