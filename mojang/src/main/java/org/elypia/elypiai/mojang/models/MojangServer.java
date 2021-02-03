/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public enum MojangServer {

    /**
     * A generic value if a server is returned that isn't recognized by Elypiai.
     * This may occur if a server address is renamed, or a new server is added
     * which Elypiai hasn't been updated to recognize yet.
     *
     * <small>
     *     Developer Notes: If this is ever returned due to a
     *     missing server address, please consider doing a PR to update this enum!
     * </small>
     */
    UNKNOWN(null),

    @SerializedName("minecraft.net")
    MINECRAFT("minecraft.net"),

    @SerializedName("session.minecraft.net")
    SESSION_MINECRAFT("session.minecraft.net"),

    @SerializedName("account.mojang.com")
    ACCOUNT_MOJANG("account.mojang.com"),

    @SerializedName("auth.mojang.com")
    AUTH_MOJANG("auth.mojang.com"),

    @SerializedName("skins.minecraft.net")
    SKINS_MINECRAFT("skins.minecraft.net"),

    @SerializedName("authserver.mojang.com")
    AUTHSERVER_MOJANG("authserver.mojang.com"),

    @SerializedName("sessionserver.mojang.com")
    SESSIONSERVER_MOJANG("sessionserver.mojang.com"),

    @SerializedName("api.mojang.com")
    API_MOJANG("api.mojang.com"),

    @SerializedName("textures.minecraft.net")
    TEXTURES_MINECRAFT("textures.minecraft.net"),

    @SerializedName("mojang.com")
    MOJANG("mojang.com");

    private final String domain;

    MojangServer(final String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public static MojangServer get(final String domain) {
        for (MojangServer property : values()) {
            final String propertyDomain = property.domain;

            if (propertyDomain != null && propertyDomain.equals(domain))
                return property;
        }

        return UNKNOWN;
    }
}
