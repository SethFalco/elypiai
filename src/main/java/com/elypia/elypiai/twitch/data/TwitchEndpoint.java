package com.elypia.elypiai.twitch.data;

public enum TwitchEndpoint {

    GET_USERS("https://api.twitch.tv/helix/users"),
    GET_STREAMS("https://api.twitch.tv/helix/streams");

    private String endpoint;

    TwitchEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
