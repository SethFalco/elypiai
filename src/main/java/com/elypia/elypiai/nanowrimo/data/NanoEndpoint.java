package com.elypia.elypiai.nanowrimo.data;

public enum NanoEndpoint {

    PARTICIPANT("https://nanowrimo.org/participants"),
    WORDCOUNT("http://nanowrimo.org/api/wordcount");

    private String endpoint;

    NanoEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
