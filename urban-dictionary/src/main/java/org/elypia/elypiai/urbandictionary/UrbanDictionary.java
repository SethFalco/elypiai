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

package org.elypia.elypiai.urbandictionary;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import org.elypia.retropia.core.HttpClientSingleton;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class UrbanDictionary {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("http://api.urbandictionary.com/v0/");
        } catch (MalformedURLException ex) {}
    }

    private UrbanDictionaryService service;

    public UrbanDictionary() {
        this(baseUrl);
    }

    public UrbanDictionary(URL url) {
        this(url, HttpClientSingleton.getClient());
    }

    public UrbanDictionary(URL url, OkHttpClient client) {
        service = new Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(UrbanDictionaryService.class);
    }

    /**
     * Definition from Urban Dictionary if result(s) are found matching exactly
     * the word or phrase provided.
     *
     * @param term Word or phrase to be defined.
     * @return
     *     REST action to request definition of the word, contains null if not
     *     definition was found.
     */
    public Single<DefineResult> getDefinitions(String term) {
        return service.getDefinitions(term);
    }

    /**
     * Returns 10 random definitions from UrbanDictionary.
     * The list of definitions can be incorporated of
     * different words.
     *
     * @return RestAction to request random definitions.
     */
    public Single<DefineResult> getRandomDefinitions() {
        return service.getRandomDefinitions();
    }

    /**
     * Single definition from Urban Dictionary from the ID of the definition.
     *
     * @param id {@link Definition#getDefinitionId()}
     * @return Definition if it exists, else an empty Optional.
     */
    public Maybe<Definition> getDefinitionById(int id) {
        Maybe<DefineResult> call = service.getDefinitionById(id);
        return call.mapOptional((results) -> (results.hasDefinitions()) ? Optional.of(results.getDefinition()) : Optional.empty());
    }

    /**
     * @param term Term to get the Urban Dictionary tooltip for.
     * @return
     *     Tooltip string which is displayed on the Urban Dictionary website
     *     when hovering over linked words in definitions.
     */
    public Single<Object> getWordTooltip(final String term) {
        Objects.requireNonNull(term, "Must provide ");
        return service.getTooltip(term);
    }
}
