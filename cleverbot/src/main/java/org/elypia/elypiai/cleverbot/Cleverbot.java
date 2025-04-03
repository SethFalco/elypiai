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

package org.elypia.elypiai.cleverbot;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.elypia.elypiai.cleverbot.data.CleverTweak;
import org.elypia.elypiai.cleverbot.deserializers.CleverResponseDeserializer;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.QueryParametersInterceptor;

import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Cleverbot {

    /**
     * Default URL we call too.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://www.cleverbot.com/");
        } catch (MalformedURLException ex) {}
    }

    private final String apiKey;
    private final CleverbotService service;

    /**
     * @param apiKey API key received from Cleverbot.
     */
    public Cleverbot(String apiKey) {
        this(apiKey, baseUrl);
    }

    /**
     * Creates the Cleverbot object in order to make requests to the Cleverbot
     * API. API key can be obtained from Cleverbot website.
     *
     * @param apiKey API key recieved upon signing up.
     * @param baseUrl URL to send HTTP requests too.
     * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
     */
    public Cleverbot(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder().addInterceptor(
                new QueryParametersInterceptor(Map.of("key", apiKey, "wrapper", "Elypiai"))
            ).build()
        );
    }

    public Cleverbot(String apiKey, URL baseUrl, OkHttpClient client) {
        this.apiKey = Objects.requireNonNull(apiKey);

        GsonBuilder builder = new GsonBuilder()
            .registerTypeAdapter(CleverResponse.class, new CleverResponseDeserializer());

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(builder.create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(CleverbotService.class);
    }

    public Single<CleverResponse> say(String input) {
        return say(input, null);
    }

    /**
     * Returns the Cleverbot response to the given input, however allows
     * the user to store and pass their CS value rather than rely
     * on the Map used internally in this API, encouraged for longer
     * conversations you may wish to continue after restarting the application.
     *
     * @param cs Cleverbot state from previously.
     * @param input Text to send to cleverbot.
     * @return REST action that represents this HTTP request.
     */
    public Single<CleverResponse> say(String input, String cs) {
        return say(input, cs, new EnumMap<>(CleverTweak.class));
    }

    public Single<CleverResponse> say(String input, String cs, Map<CleverTweak, Integer> tweaks) {
        Objects.requireNonNull(tweaks);

        Integer wacky = tweaks.get(CleverTweak.WACKY);
        Integer talkitive = tweaks.get(CleverTweak.TALKATIVE);
        Integer attentive = tweaks.get(CleverTweak.ATTENTIVE);

        return service.say(input, cs, wacky, talkitive, attentive);
    }

    public String getApiKey() {
        return apiKey;
    }
}
