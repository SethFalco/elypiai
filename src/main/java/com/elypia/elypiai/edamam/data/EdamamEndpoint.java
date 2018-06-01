package com.elypia.elypiai.edamam.data;

public enum EdamamEndpoint {

    SEARCH("https://api.edamam.com/search");

    private final String ENDPOINT;

    EdamamEndpoint(String endpoint) {
        ENDPOINT = endpoint;
    }

    public String getEndpoint() {
        return ENDPOINT;
    }
}
