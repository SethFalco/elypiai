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

package fun.falco.elypiai.yugioh;

import java.net.MalformedURLException;
import java.net.URL;

import org.elypia.retropia.core.HttpClientSingleton;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Yugioh {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("http://yugiohprices.com/api/");
        } catch (MalformedURLException ex) {}
    }

    private YugiohService service;

    public Yugioh() {
        this(baseUrl);
    }

    public Yugioh(URL url) {
        this(url, HttpClientSingleton.getClient());
    }

    public Yugioh(URL url, OkHttpClient client) {
        service = new Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(YugiohService.class);
    }

    /**
     * Search the Yu-Gi-Oh Prices API for the YuGiOh card requested.
     * This only returns a single card and the spelling of the card
     * must be exact, including hyphens (-) and spacing.
     * Can return a monster or spell/trap card.
     *
     * @param name Yu-Gi-Oh card to search for, must match card name exactly.
     */
    public Maybe<TradingCard> getCard(String name) {
        return service.getCard(name);
    }
}
