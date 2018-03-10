package com.elypia.elypiai.google.translate.data;

public enum GoogleTranslateEndpoint {

    LANGUAGES("https://translation.googleapis.com/language/translate/v2/languages"),
    TRANSLATE("https://translation.googleapis.com/language/translate/v2"),
    DETECT("https://translation.googleapis.com/language/translate/v2/detect");

    private String endpoint;

    GoogleTranslateEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
