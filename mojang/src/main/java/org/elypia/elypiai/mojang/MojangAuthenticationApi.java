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

import okhttp3.OkHttpClient;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * The portion of the Mojang API that connects to the
 * {@link org.elypia.elypiai.mojang.models.MojangServer#API_MOJANG API server}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MojangAuthenticationApi {

    private static final Logger logger = LoggerFactory.getLogger(MojangAuthenticationApi.class);

    /**
     * <p>The default URL we call too.</p>
     * <p>Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.</p>
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://authserver.mojang.com/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    /** The {@link Retrofit} wrapper around the API. */
    private final MojangAuthenticationService service;

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangAuthenticationApi() {
        this(baseUrl);
    }

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangAuthenticationApi(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public MojangAuthenticationApi(URL baseUrl, OkHttpClient client) {
        Objects.requireNonNull(baseUrl);
        Objects.requireNonNull(client);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(MojangAuthenticationService.class);
    }

    /**
     * @return The default base URL. This may not be the same as the base URL
     * that was passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
