package com.elypia.elypiai.utils;

import java.util.Objects;

public enum Language {

    AFRIKAANS(null, "AF"),
    AMHARIC(null, "AM"),
    ARABIC(null, "AR"),
    AZERVAIKANI(null, "AZ"),
    BERLARUSIAN(null, "BE"),
    BULGARIAN(null, "BG"),
    BENGALI(null, "BN"),
    BOSNIAN(null, "BS"),
    CATALAN(null, "CA"),
    CEBUANO(null, "CEB"),
    CORSICAN(null, "CO"),
    CZECH(null, "CS"),
    WELSH(null, "CY"),
    DANISH(null, "DA"),
    GERMAN(null, "DE"),
    GREEK(null, "EL"),
    ENGLISH(null, "EN"),
    ESPERANTO(null, "EO"),
    ESTONIAN(null, "ET"),
    BASQUE(null, "EU"),
    PERSIAN(null, "FA"),
    FINNISH(null, "FI"),
    FRENCH(null, "FR"),
    WESTERN_FRISTIAN(null, "FY"),
    IRISH(null, "GA"),
    GAELIC(null, "GD"),
    GALICAN(null, "GL"),
    GUJARATI(null, "GU"),
    HAUSA(null, "HA"),
    HAWAIIAN(null, "HAW"),
    HINDI(null, "HI"),
    HMONG(null, "HMN"),
    CROATION(null, "HR"),
    HUNGARIAN(null, "HU"),
    ARMENIAN(null, "HY"),
    INDONESIAN(null, "ID"),
    IGBO(null, "IG"),
    ICELANDIC(null, "IS"),
    ITALIAN(null, "IT"),
    JAPANESE(null, "JA"),
    GEORGIAN(null, "KA"),
    KAZAKH(null, "KK"),
    CENTRAL_KHMER(null, "KM"),
    KOREAN(null, "KO"),
    KURDISH(null, "KU"),
    LATIN(null, "LA"),
    LUXEMBOURGISH(null, "LB"),
    LITHUANIAN(null, "LT"),
    MALAGASY(null, "MG"),
    MAORI(null, "MI"),
    NEPALI(null, "NE"),
    DUTCH(null, "NL"),
    NORWEGIAN(null, "NO"),
    CHICHEWA(null, "NY"),
    PANJABI(null, "PA"),
    POLISH(null, "PL"),
    PUSHTO(null, "PS"),
    ROMANIAN(null, "RO"),
    RUSSIAN(null, "RU"),
    SINHALA(null, "SI"),
    SLOVAK(null, "SK"),
    SLOVENIAN(null, "SL"),
    SAMOAN(null, "SM"),
    SHONA(null, "SN"),
    SOMALI(null, "SO"),
    ALBANIAN(null, "SQ"),
    SERBIAN(null, "SR"),
    SUNDANESE(null, "SU"),
    SWEDISH(null, "SV"),
    SWAHILI(null, "SW"),
    TAMIL(null, "TA"),
    TELUGU(null, "TE"),
    TAJIK(null, "TG"),
    TAGALOG(null, "TL"),
    TURKISH(null, "TR"),
    UKRAINIAN(null, "UK"),
    URDU(null, "UR"),
    UZBEK(null, "UZ"),
    VIETNAMESE(null, "VI"),
    XHOSA(null, "XH"),
    YIDDISH(null, "YI"),
    YARUBA(null, "YO"),
    CHINESE(null, "ZH"),
    ZULU(null, "ZU");

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
