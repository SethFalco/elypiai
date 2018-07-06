package com.elypia.elypiai.utils;

import com.google.gson.annotations.SerializedName;

public enum Country {

    @SerializedName(value = "AF", alternate = "af")
    AFGHANISTAN("Afghanistan", "AFG", "AF", "🇦🇫"),

    @SerializedName(value = "AL", alternate = "al")
    ALBANIA("Albania", "ALB", "AL", "🇦🇱"),

    @SerializedName(value = "DZ", alternate = "dz")
    ALGERIA("Algeria", "DZA","DZ", "🇩🇿"),

    @SerializedName(value = "AM", alternate = "am")
    ARMENIA("Armenia", "ARM", "AM", "🇦🇲"),

    @SerializedName(value = "AW", alternate = "aw")
    ARUBA("Aruba", "ABW", "AW", "🇦🇼"),

    @SerializedName(value = "AU", alternate = "au")
    AUSTRALIA("Australia", "AUS", "AU", "🇦🇺"),

    @SerializedName(value = "AT", alternate = "at")
    AUSTRIA("Austria", "AUT", "AT", "🇦🇹"),

    @SerializedName(value = "AZ", alternate = "az")
    AZERBAIJAN("Azerbaijan", "AZE", "AZ", "🇦🇿"),

    @SerializedName(value = "BH", alternate = "bh")
    BAHRAIN("Bahrain", "BHR", "BH", "🇧🇭"),

    @SerializedName(value = "BY", alternate = "by")
    BELARUS("Belarus", "BLR", "BY", "🇧🇾"),

    @SerializedName(value = "BE", alternate = "be")
    BELGIUM("Belgium", "BEL", "BE", "🇧🇪"),

    @SerializedName(value = "BD", alternate = "bd")
    BENGLADESH("Bengladesh", "BGD", "BD", "🇧🇩"),

    @SerializedName(value = "BA", alternate = "ba")
    BOSNIA("Bosnia", "BIH", "BA", "🇧🇦"),

    @SerializedName(value = "BG", alternate = "bg")
    BULGARIA("Bulgaria", "BGR", "BG", "🇧🇬"),

    @SerializedName(value = "CA", alternate = "ca")
    CANADA("Canada", "CAN", "CA", "\uD83C\uDDE8\uD83C\uDDE6"),

    @SerializedName(value = "KH", alternate = "kh")
    CAMBODIA("Cambodia", "KHM", "KH", "🇰🇭"),

    @SerializedName(value = "TD", alternate = "td")
    CHAD("Chad", "TCD", "TD", "🇹🇩"),

    @SerializedName(value = "CN", alternate = "cn")
    CHINA("China", "CHN", "CN", "🇨🇳"),

    @SerializedName(value = "KM", alternate = "km")
    COMOROS("Comoros", "COM", "KM", "🇰🇲"),

    @SerializedName(value = "HR", alternate = "hr")
    CROATIA("Croatia", "HRV", "HR", "🇭🇷"),

    @SerializedName(value = "CW", alternate = "cw")
    CURACAO("Curacao", "CUW", "CW", "🇨🇼"),

    @SerializedName(value = "CY", alternate = "cy")
    CYPRUS("Cyprus", "CYP", "CY", "🇨🇾"),

    @SerializedName(value = "CZ", alternate = "cz")
    CZECH_REPUBLIC("Czech Republic", "CZE", "CZ", "🇨🇿"),

    @SerializedName(value = "CD", alternate = "cd")
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO("Democratic Republic of the Congo", "COD", "CD", "🇨🇩"),

    @SerializedName(value = "DK", alternate = "dk")
    DENMARK("Denmark", "DNK", "DK", "🇩🇰"),

    @SerializedName(value = "DJ", alternate = "dj")
    DJIBOUTI("Djibouti", "DJI", "DJ", "🇩🇯"),

    @SerializedName(value = "EG", alternate = "eg")
    EGYPT("Egypt", "EGY", "EG", "🇪🇬"),

    @SerializedName(value = "ER", alternate = "er")
    ERITREA("Eritrea", "ERI", "ER", "🇪🇷"),

    @SerializedName(value = "EE", alternate = "ee")
    ESTONIA("Estonia", "EST", "EE", "🇪🇪"),

    @SerializedName(value = "ET", alternate = "et")
    ETHIOPIA("Ethiopia", "ETH", "ET", "🇪🇹"),

    @SerializedName(value = "FO", alternate = "fo")
    FAROE_ISLANDS("Faroe Islands", "FRO", "FO", "🇫🇴"),

    @SerializedName(value = "FI", alternate = "fi")
    FINLAND("Finland", "FIN", "FI", "🇫🇮"),

    @SerializedName(value = "FR", alternate = "fr")
    FRANCE("France", "FRA", "FR", "🇫🇷"),

    @SerializedName(value = "GE", alternate = "ge")
    GEORGIA("Georgia", "GEO", "GE", "🇬🇪"),

    @SerializedName(value = "DE", alternate = "de")
    GERMANY("Germany", "DEU", "DE", "🇩🇪"),

    @SerializedName(value = "GR", alternate = "gr")
    GREECE("Greece", "GRC", "GR", "🇬🇷"),

    @SerializedName(value = "HU", alternate = "hu")
    HUNGARY("Hungary", "HUN", "HU", "🇭🇺"),

    @SerializedName(value = "HK", alternate = "hk")
    HONG_KONG("Hong Kong", "HKG", "HK", "🇭🇰"),

    @SerializedName(value = "IS", alternate = "is")
    ICELAND("Iceland", "ISL", "IS", "🇮🇸"),

    @SerializedName(value = "IN", alternate = "in")
    INDIA("India", "IND", "IN", "🇮🇳"),

    @SerializedName(value = "id", alternate = "id")
    INDONESIA("Indonesia", "IDN", "ID", "🇮🇩"),

    @SerializedName(value = "IR", alternate = "ir")
    IRAN("Iran", "IRN", "IR", "🇮🇷"),

    @SerializedName(value = "IQ", alternate = "iq")
    IRAQ("Iraq", "IRQ", "IQ", "🇮🇶"),

    @SerializedName(value = "IE", alternate = "ie")
    IRELAND("Republic of Ireland", "IRL", "IE", "🇮🇪"),

    @SerializedName(value = "IL", alternate = "il")
    ISRAEL("Israel", "ISR", "IL", "🇮🇱"),

    @SerializedName(value = "IT", alternate = "it")
    ITALY("Italy", "ITA", "IT", "🇮🇹"),

    @SerializedName(value = "JP", alternate = "jp")
    JAPAN("Japan", "JPN", "JP", "🇯🇵"),

    @SerializedName(value = "JO", alternate = "jo")
    JORDAN("Jordan", "JOR", "JO", "🇯🇴"),

    @SerializedName(value = "KZ", alternate = "kz")
    KAZAKHSTAN("Kazakhstan", "KAZ", "KZ", "🇰🇿"),

    @SerializedName(value = "KE", alternate = "ke")
    KENYA("Kenya", "KEN", "KE", "🇰🇪"),

    @SerializedName(value = "KW", alternate = "kw")
    KUWAIT("Kuwait", "KWT", "KW", "🇰🇼"),

    @SerializedName(value = "KG", alternate = "kg")
    KYRGYZSTAN("Kyrgyzstan", "KGZ", "KG", "🇰🇬"),

    @SerializedName(value = "LB", alternate = "lb")
    LEBANON("Lebanon", "LBN", "LB", "🇱🇧"),

    @SerializedName(value = "LY", alternate = "ly")
    LIBYA("Libya", "LBY", "LY", "🇱🇾"),

    @SerializedName(value = "LI", alternate = "li")
    LIECHTENSTEIN("Liechtenstein", "LIE", "LI", "🇱🇮"),

    @SerializedName(value = "LT", alternate = "lt")
    LITHUANIA("Lithuania", "LTU", "LT", "🇱🇹"),

    @SerializedName(value = "LU", alternate = "lu")
    LUXEMBOURG("Luxembourg", "LUX", "LU", "🇱🇺"),

    @SerializedName(value = "MO", alternate = "mo")
    MACAU("Macau", "MAC", "MO", "🇲🇴"),

    @SerializedName(value = "MG", alternate = "mg")
    MADAGASCAR("Madagascar", "MDG", "MG", "🇲🇬"),

    @SerializedName(value = "MW", alternate = "mw")
    MALAWI("Malawi", "MWI", "MW", "🇲🇼"),

    @SerializedName(value = "MR", alternate = "mr")
    MAURITANIA("Mauritania", "MRT", "MR", "🇲🇷"),

    @SerializedName(value = "MD", alternate = "md")
    MOLDOVA("Moldova", "MDA", "MD", "🇲🇩"),

    @SerializedName(value = "MA", alternate = "ma")
    MOROCCO("Morocco", "MAR", "MA", "🇲🇦"),

    @SerializedName(value = "NA", alternate = "na")
    NAMBIA("Nambia", "NAM", "NA", "🇳🇦"),

    @SerializedName(value = "NL", alternate = "nl")
    NETHERLANDS("Netherlands", "NLD", "NL", "🇳🇱"),

    @SerializedName(value = "NZ", alternate = "nz")
    NEW_ZEALAND("New Zealand", "NZL", "NZ", "🇳🇿"),

    @SerializedName(value = "NP", alternate = "np")
    NEPAL("Nepal", "NPL", "NP", "🇳🇵"),

    @SerializedName(value = "NE", alternate = "ne")
    NIGER("Niger", "NER", "NE", "🇳🇪"),

    @SerializedName(value = "NG", alternate = "ng")
    NIGERIA("Nigeria", "NGA", "NG", "🇳🇬"),

    @SerializedName(value = "KP", alternate = "kp")
    NORTH_KOREA("North Korea", "PRK", "KP", "🇰🇵"),

    @SerializedName(value = "NO", alternate = "no")
    NORWAY("Norway", "NOR", "NO", "🇳🇴"),

    @SerializedName(value = "OM", alternate = "om")
    OMAN("Oman", "OMN", "OM", "🇴🇲"),

    @SerializedName(value = "PS", alternate = "ps")
    PALESTINE("State of Palestine", "PSE", "PS", "🇵🇸"),

    @SerializedName(value = "PK", alternate = "pk")
    PAKISTAN("Pakistan", "PAK", "PK", "🇵🇰"),

    @SerializedName(value = "PH", alternate = "ph")
    PHILIPPINES("Philippines", "PHL", "PH", "🇵🇭"),

    @SerializedName(value = "PL", alternate = "pl")
    POLAND("Poland", "POL", "PL", "🇵🇱"),

    @SerializedName(value = "QA", alternate = "qa")
    QATAR("Qatar", "QAT", "QA", "🇶🇦"),

    @SerializedName(value = "RO", alternate = "ro")
    ROMANIA("Romania", "ROU", "RO", "🇷🇴"),

    @SerializedName(value = "RU", alternate = "ru")
    RUSSIA("Russia", "RUS", "RU", "🇷🇺"),

    @SerializedName(value = "RW", alternate = "rw")
    RWANDA("Rewanda", "RWA", "RW", "🇷🇼"),

    @SerializedName(value = "SA", alternate = "sa")
    SAUDI_ARABIA("Saudi Arabia", "SAU", "SA", "🇸🇦"),

    @SerializedName(value = "ws", alternate = "ws")
    SAOMA("Samoa", "WSM", "WS", "🇼🇸"),

    @SerializedName(value = "RS", alternate = "rs")
    SERBIA("Serbia", "SRB", "RS", "🇷🇸"),

    @SerializedName(value = "SG", alternate = "sg")
    SINGAPORE("Singapore", "SGP", "SG", "🇸🇬"),

    @SerializedName(value = "SX", alternate = "sx")
    SINT_MAARTEN("Sint Maarten", "SXM", "SX", "🇸🇽"),

    @SerializedName(value = "SK", alternate = "sk")
    SLOVAKIA("Slovakia", "SVK", "SK", "🇸🇰"),

    @SerializedName(value = "SI", alternate = "si")
    SLOVENIA("Slovenia", "SVN", "SI", "\uD83C\uDDF8\uD83C\uDDEE"),

    @SerializedName(value = "SO", alternate = "so")
    SOMALIA("Somalia", "SOM", "SO", "🇸🇴"),

    @SerializedName(value = "ZA", alternate = "za")
    SOUTH_AFRICA("South Africa", "ZAF", "ZA", "🇿🇦"),

    @SerializedName(value = "KR", alternate = "kr")
    SOUTH_KOREA("South Korea", "KOR", "KR", "🇰🇷"),

    @SerializedName(value = "ES", alternate = "es")
    SPAIN("Spain", "ESP", "ES", "🇪🇸"),

    @SerializedName(value = "LK", alternate = "lk")
    SRI_LANKA("Sri Lanka", "LKA", "LK", "🇱🇰"),

    @SerializedName(value = "SD", alternate = "sd")
    SUDAN("Sudan", "SDN", "SD", "🇸🇩"),

    @SerializedName(value = "SR", alternate = "sr")
    SURINAME("Suriname", "SUR", "SR", "🇸🇷"),

    @SerializedName(value = "SE", alternate = "se")
    SWEDEN("Sweden", "SWE", "SE", "🇸🇪"),

    @SerializedName(value = "CH", alternate = "ch")
    SWITZERLAND("Switzerland", "CHE", "CH", "🇨🇭"),

    @SerializedName(value = "SY", alternate = "sy")
    SYRIA("Syria", "SYR", "SY", "🇸🇾"),

    @SerializedName(value = "TW", alternate = "tw")
    TAIWAN("Taiwan", "TWN", "TW", "🇹🇼"),

    @SerializedName(value = "TJ", alternate = "tj")
    TAJIKISTAN("Tajikistan", "TJK", "TJ", "🇹🇯"),

    @SerializedName(value = "TZ", alternate = "tz")
    TANZANIA("Tanzania", "TZA", "TZ", "🇹🇿"),

    @SerializedName(value = "TN", alternate = "tn")
    TUNISIA("Tunisia", "TUN", "TN", "🇹🇳"),

    @SerializedName(value = "TR", alternate = "tr")
    TURKEY("Turkey", "TUR", "TR", "🇹🇷"),

    @SerializedName(value = "AE", alternate = "ae")
    UNITED_ARAB_EMIRATES("United Arab Emirates", "ARE", "AE", "🇦🇪"),

    @SerializedName(value = "GB", alternate = "gb")
    UNITED_KINGDOM("United Kingdom", "GBR", "GB", "🇬🇧"),

    @SerializedName(value = "US", alternate = "us")
    UNITED_STATES("United States of America", "USA", "US", "🇺🇸"),

    @SerializedName(value = "UG", alternate = "ug")
    UGANDA("Uganda", "UGA", "UG", "🇺🇬"),

    @SerializedName(value = "UA", alternate = "ua")
    UKRAINE("Ukraine", "UKR", "UA", "🇺🇦"),

    @SerializedName(value = "UZ", alternate = "uz")
    UZBEKISTAN("Uzbekistan", "UZB", "UZ", "🇺🇿"),

    @SerializedName(value = "VN", alternate = "vn")
    VIETNAM("Vietnam", "VNM", "VN", "🇻🇳"),

    @SerializedName(value = "YE", alternate = "ye")
    YEMEN("Yemen", "YEM", "YE", "🇾🇪"),

    @SerializedName(value = "ZW", alternate = "zw")
    ZIMBABWE("Zimbabwe", "ZWE", "ZW", "🇿🇼");

    private final String COUNTRY;
    private final String ISO_CODE;
    private final String ISO_3166;
    private final String UNICODE_EMOTE;

    Country(String country, String isoCode, String iso3166, String unicodeEmote) {
        COUNTRY = country;
        ISO_CODE = isoCode;
        ISO_3166 = iso3166;
        UNICODE_EMOTE = unicodeEmote;
    }

    public String getCountryName() {
        return COUNTRY;
    }

    public String getIsoCode() {
        return ISO_CODE;
    }

    public String getIso3166() {
        return ISO_3166;
    }

    public String getUnicodeEmote() {
        return UNICODE_EMOTE;
    }
}
