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
import java.util.Map;
import java.util.Objects;

import org.elypia.retropia.core.HttpClientSingleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import fun.falco.elypiai.mojang.deserializers.ServerStatusDeserializer;
import fun.falco.elypiai.mojang.models.MojangServer;
import fun.falco.elypiai.mojang.models.ServerStatus;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangStatusApi {

    /**
     * Default URL we call too.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://status.mojang.com/");
        } catch (MalformedURLException ex) {}
    }

    /** {@link Retrofit} wrapper around the API. */
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
     * @return Status of all Mojang servers.
     */
    public Single<Map<MojangServer, ServerStatus>> getServerStatuses() {
        return service.getServerStatuses();
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
