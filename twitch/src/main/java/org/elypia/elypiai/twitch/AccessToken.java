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

package org.elypia.elypiai.twitch;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.common.core.data.AuthenticationType;

import java.util.Collection;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
