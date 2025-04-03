/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.elypia.elypiai.mojang.deserializers.InstantDeserializer;
import org.elypia.elypiai.mojang.models.MinecraftProfile;
import org.elypia.elypiai.mojang.models.MojangServer;
import org.elypia.retropia.core.HttpClientSingleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Portion of the Mojang API that connects to the
 * {@link MojangServer#SESSIONSERVER_MOJANG session server}.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangSessionApi {

    /**
     * Default URL we call too.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://sessionserver.mojang.com/");
        } catch (MalformedURLException ex) {}
    }

    /** {@link Retrofit} wrapper around the API. */
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

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantDeserializer())
            .create();

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MojangSessionService.class);
    }

    /**
     * Has a much stricter rate limit: You can request the same profile once per
     * minute, however you can send as many unique requests as you like.
     *
     * @param uuid Players UUID.
     * @return
     *     Player's username plus any additional information about them (e.g.
     *     skins)
     */
    public Single<MinecraftProfile> getMinecraftProfile(final UUID uuid) {
        return service.getMinecraftProfile(uuid, true);
    }

    /**
     * @return
     *     SHA1 hashes used to check server addresses against when the client
     *     tries to connect.
     */
    public Single<List<String>> getBlockedServerList() {
        return service.getBlockedServers().map((response) -> {
            final String[] split = response.split("\n");
            return List.of(split);
        });
    }

    /**
     * @return
     *     Default base URL. This may not be the same as the base URL that was
     *     passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
