package com.elypia.elypiai.twitch.service;

import com.elypia.elypiai.common.core.data.AuthenticationType;
import com.elypia.elypiai.twitch.AccessToken;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TwitchAppService {

    /**
     * @param clientId Application Client-Id
     * @param secret Application Client-Secret
     * @param type The type get authentication used, normally {@link AuthenticationType#BEARER}
     * @param scope The scopes required by your application.
     * @return
     */
    @POST("oauth2/token")
    Call<AccessToken> getToken(
        @Header("Client-Id") String clientId,
        @Query("client_secret") String secret,
        @Query("grant_type") String type,
        @Query("scope") String scope
    );

    /**
     * @param clientId Application Client-Id
     * @param accessToken The access token made using {@link #getToken(String, String, String, String)}
     * @return
     */
    @POST("oauth2/revoke")
    Call<Void> revokeToken(
        @Header("Client-Id") String clientId,
        @Query("token") String accessToken
    );
}
