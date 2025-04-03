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

package fun.falco.elypiai.mojang;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import org.elypia.retropia.core.HttpClientSingleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * The portion of the Mojang API that connects to the
 * {@link fun.falco.elypiai.mojang.models.MojangServer#API_MOJANG API server}.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangAuthenticationApi {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://authserver.mojang.com/");
        } catch (MalformedURLException ex) {}
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
     * @return
     *     Default base URL. This may not be the same as the base URL that was
     *     passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
