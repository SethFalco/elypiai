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

package org.elypia.elypiai.mojang;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.mojang.models.MinecraftProfile;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The portion of the Mojang API that connects to the
 * {@link org.elypia.elypiai.mojang.models.MojangServer#SESSIONSERVER_MOJANG session server}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MojangSessionApi {

    private static final Logger logger = LoggerFactory.getLogger(MojangSessionApi.class);

    /**
     * <p>The default URL we call too.</p>
     * <p>Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.</p>
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://sessionserver.mojang.com/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    /** The {@link Retrofit} wrapper around the API. */
    private final MojangSessionService service;

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangSessionApi() {
        this(baseUrl);
    }

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangSessionApi(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public MojangSessionApi(URL baseUrl, OkHttpClient client) {
        Objects.requireNonNull(client);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MojangSessionService.class);
    }

    /**
     * This has a much stricter rate limit: You can request the same profile
     * once per minute, however you can send as many unique requests as you like.
     *
     * @param uuid The players UUID.
     * @return The player's username plus any additional information about them (e.g. skins)
     */
    public Single<MinecraftProfile> getMinecraftProfile(final UUID uuid) {
        return service.getMinecraftProfile(uuid, true);
    }

    /**
     * @return A list of SHA1 hashes used to check server
     * addresses against when the client tries to connect.
     */
    public Single<List<String>> getBlockedServerList() {
        return service.getBlockedServers().map((response) -> {
            final String[] split = response.split("\n");
            return List.of(split);
        });
    }

    /**
     * @return The default base URL. This may not be the same as the base URL
     * that was passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
