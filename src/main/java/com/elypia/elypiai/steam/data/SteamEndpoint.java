package com.elypia.elypiai.steam.data;

public enum SteamEndpoint {

    GET_STEAM_ID("http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/"),
    GET_USER("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/"),
    GET_LIB("http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/");

    private String endpoint;

    SteamEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
