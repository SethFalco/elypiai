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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import org.elypia.elypiai.mojang.deserializers.ServerStatusDeserializer;
import org.elypia.elypiai.mojang.models.MojangServer;
import org.elypia.elypiai.mojang.models.ServerStatus;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MojangStatusApi {

    private static final Logger logger = LoggerFactory.getLogger(MojangStatusApi.class);

    /**
     * <p>The default URL we call too.</p>
     * <p>Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.</p>
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://status.mojang.com/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    /** The {@link Retrofit} wrapper around the API. */
    private final MojangStatusService service;

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangStatusApi() {
        this(baseUrl);
    }

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangStatusApi(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public MojangStatusApi(URL baseUrl, OkHttpClient client) {
        Objects.requireNonNull(client);

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<Map<MojangServer, ServerStatus>>(){}.getType(), new ServerStatusDeserializer())
            .create();

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MojangStatusService.class);
    }

    /**
     * @return The status of all Mojang servers.
     */
    public Single<Map<MojangServer, ServerStatus>> getServerStatuses() {
        return service.getServerStatuses();
    }

    /**
     * @return The default base URL. This may not be the same as the base URL
     * that was passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
