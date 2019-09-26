/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.twitch.service;

import org.elypia.elypiai.common.core.data.AuthenticationType;
import org.elypia.elypiai.twitch.AccessToken;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
