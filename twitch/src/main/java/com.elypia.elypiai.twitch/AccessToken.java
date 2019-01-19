package com.elypia.elypiai.twitch;

import com.elypia.elypiai.restutils.data.AuthenticationType;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;

public class AccessToken {

    @SerializedName("access_token")
    private String token;

    /**
     * This is not used for app access tokens.
     */
    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("expires_in")
    private int expires;

    private Collection<String> scopes;

    @SerializedName("token_type")
    private AuthenticationType authentication;

    public void create() {

    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public int getExpires() {
        return expires;
    }

    public Collection<String> getScopes() {
        return scopes;
    }

    public AuthenticationType getAuthentication() {
        return authentication;
    }
}
