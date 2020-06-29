/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.weblate;

import okhttp3.OkHttpClient;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.TokenAuthorizationInterceptor;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.Objects;

/**
 * @author seth@elypia.org
 * @since 4.2.2
 */
public class Weblate {

    private static final Logger logger = LoggerFactory.getLogger(Weblate.class);

    /**
     * <p>The default URL we call too.</p>
     * <p>Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.</p>
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://hosted.weblate.org/api/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    /** The users API key, or null if we're consuming the API through unauthenticated requests. */
    private final String apiKey;

    /** The {@link Retrofit} wrapper around the API. */
    private final WeblateService service;

    /**
     * Creates an instance of the Weblate API for unauthenticated use.
     *
     * @see <a href="https://docs.weblate.org/en/latest/api.html#authentication-and-generic-parameters">Unauthenticated Access to Weblate API</a>
     */
    public Weblate() {
        this(null);
    }

    /**
     * Creates an instance of the Weblate API.
     * You can use this to get information on translation projects on Weblate.
     * This defaults the the baseUrl to the public hosted instance availabe at
     * {@link #baseUrl https://hosted.weblate.org/api/}.
     *
     * @param apiKey The API key obtained from your Weblate profile page,
     * or null for unauthenticated access.
     * @see <a href="https://docs.weblate.org/en/latest/api.html#authentication-and-generic-parameters">Unauthenticated Access to Weblate API</a>
     */
    public Weblate(String apiKey) {
        this(apiKey, baseUrl);
    }

    /**
     * Creates an instance of the Weblate API for a given host.
     *
     *
     * @param apiKey The API key, or null for unauthenticated access to the API.
     * @param baseUrl The instance base URL, in a format like {@link #baseUrl https://hosted.weblate.org/api/}.
     */
    public Weblate(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder().addInterceptor(new TokenAuthorizationInterceptor(apiKey)).build()
        );
    }

    public Weblate(String apiKey, URL baseUrl, OkHttpClient client) {
        this.apiKey = apiKey;
        Objects.requireNonNull(client);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WeblateService.class);
    }
}
