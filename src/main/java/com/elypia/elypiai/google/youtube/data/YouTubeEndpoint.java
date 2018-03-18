package com.elypia.elypiai.google.youtube.data;

public enum YouTubeEndpoint {

    GET_MEDIA("https://www.googleapis.com/youtube/v3/search");

    private String endpoint;

    YouTubeEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
