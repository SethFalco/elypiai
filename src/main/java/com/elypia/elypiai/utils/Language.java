package com.elypia.elypiai.utils;

import java.util.Objects;

public enum Language {

    AFRIKAANS("af"),
    AMHARIC("am"),
    ARABIC("ar"),
    AZERVAIKANI("ar"),
    BERLARUSIAN("be"),
    BULGARIAN("bg"),
    BENGALI("bn"),
    BOSNIAN("bs"),
    CATALAN("ca"),
    BENUANO("ceb"),
    CORSICAN("co"),
    CZECH("cs"),
    WELSH("cy"),
    DANISH("da"),
    GERMAN("de"),
    GREEK("el"),
    ENGLISH("en", "\uD83C\uDDEC\uD83C\uDDE7", "\uD83C\uDDFA\uD83C\uDDF8"),
    ESPERANTO("eo"),
    ESTONIAN("et"),
    BASQUE("eu"),
    PERSIAN("fa"),
    FINNISH("fi"),
    FRENCH("fr", "\uD83C\uDDEB\uD83C\uDDF7"),
    WESTERN_FRISTIAN("fy"),
    IRISH("ga"),
    GAELIC("gd"),
    GALICAN("gl"),
    GUJARATI("gu"),
    HAUSA("ha"),
    HAWAIIAN("haw"),
    HINDI("hi"),
    HMONG("hmn"),
    CROATION("hr"),
    HUNGARIAN("hu"),
    ARMENIAN("hy"),
    INDONESIAN("id"),
    IGBO("ig"),
    ICELANDIC("is"),
    ITALIAN("it"),
    JAPANESE("ja"),
    GEORGIAN("ka"),
    KAZAKH("kk"),
    CENTRAL_KHMER("km"),
    KOREAN("ko"),
    KURDISH("ku"),
    LATIN("la"),
    LUXEMBOURGISH("lb"),
    LITHUANIAN("lt"),
    MALAGASY("mg"),
    MAORI("mi"),
    NEPALI("ne"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    CHICHEWA("ny"),
    PANJABI("pa"),
    POLISH("pl"),
    PUSHTO("ps"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SINHALA("si"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SAMOAN("sm"),
    SHONA("sn"),
    SOMALI("so"),
    ALBANIAN("sq"),
    SERBIAN("sr"),
    SUNDANESE("su"),
    SWEDISH("sv"),
    SWAHILI("sw"),
    TAMIL("ta"),
    TELUGU("te"),
    TAJIK("tg"),
    TAGALOG("tl"),
    TURKISH("tr"),
    UKRAINIAN("uk"),
    URDU("ur"),
    UZBEK("uz"),
    VIETNAMESE("vi"),
    XHOSA("xh"),
    YIDDISH("yi"),
    YARUBA("yo"),
    CHINESE("zh"),
    ZULU("zu");

    private String code;
    private String[] flags;

    Language(String code, String... flags) {
        this.code = code;
        this.flags = flags;
    }

    public String getCode() {
        return code;
    }

    public String[] getFlags() {
        return flags;
    }

    public static Language getByCode(String code) {
        Objects.requireNonNull("code");

        for (Language language : values()) {
            if (language.code == code || language.code.equals(code))
                return language;
        }

        return null;
    }
}
