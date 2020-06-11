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

package org.elypia.elypiai.steam;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.reactivex.rxjava3.core.*;
import okhttp3.*;
import org.elypia.elypiai.steam.deserializers.*;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Steam {

    /** A regular expression that matches against profile urls and returns the username or id. */
    private static final Pattern VANITY_URL = Pattern.compile("^(?:https?://)?steamcommunity\\.com/id/([^/]+)/?$");

    private static final Logger logger = LoggerFactory.getLogger(Steam.class);

    /**
     * The default URL we call too. <br>
     * Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("http://api.steampowered.com/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    private final String apiKey;
    private final SteamService service;

    /**
     * The Steam API allows calls to basic Steam information
     * as well as user information such as query the inventory
     * or obtaining stats.
     *
     * @param apiKey API key obtained from Steam.
     */

    public Steam(String apiKey) {
        this(baseUrl, apiKey);
    }

    public Steam(URL baseUrl, String apiKey) {
        this.apiKey = Objects.requireNonNull(apiKey);

        OkHttpClient client = HttpClientSingleton.getBuilder()
            .addInterceptor((chain) -> {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            })
            .build();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(SteamSearch.class, new SteamSearchDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamGame>>(){}.getType(), new SteamGameDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamUser>>(){}.getType(), new SteamUserDeserializer(this));

        service = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(SteamService.class);
    }

    /**
     * @param vanityUrl The custom url entirely, or custom url route after id.
     * @return The search result of if a user was found or not.
     */
    public Single<SteamSearch> getIdFromVanityUrl(String vanityUrl) {
        String name = getUsernameFromUrl(vanityUrl);
        return service.getSteamId((name == null) ? vanityUrl : name);
    }

    /**
     * Returns a list of steam players that are returned based on the IDs
     * provided.
     *
     * @param ids The IDs of all steam players to return.
     * @return A request action which will return the results, never null.
     */
    public Single<List<SteamUser>> getUsers(long... ids) {
        if (ids == null || ids.length == 0)
            throw new IllegalArgumentException("Must specify at least one user to fetch.");

        StringJoiner joiner = new StringJoiner(",");

        for (long id : ids)
            joiner.add(String.valueOf(id));

        return service.getUsers(joiner.toString());
    }

    /**
     * Get the Steam users Library, do note the first time
     * the method is called for each SteamProfile; it consumes
     * another API call, the Library is cached however upon
     * method call. This contains a list of games the steam user owns
     * (or has played for free games) sorted from RecentPlaytime, and
     * when RecentPlaytime is 0, from TotalPlaytime.
     *
     * @param id Steam user to obtain library for.
     * @return An rest action which will return a list of steam games.
     */
    public Maybe<List<SteamGame>> getLibrary(long id) {
        return getLibrary(id, true);
    }

    public Maybe<List<SteamGame>> getLibrary(long id, boolean freeGames) {
        return getLibrary(id, freeGames, true);
    }

    public Maybe<List<SteamGame>> getLibrary(long id, boolean freeGames, boolean info) {
        return service.getLibrary(id, freeGames ? 1 : 0, info ? 1 : 0);
    }

    /**
     * @param vanityUrl The URL to a users stream profile.
     * @return The identifying portion of the URL which represents
     * the users custom URL.
     */
    public static String getUsernameFromUrl(String vanityUrl) {
        Matcher matcher = VANITY_URL.matcher(vanityUrl);
        return (matcher.find()) ? matcher.group(1) : null;
    }

    public String getApiKey() {
        return apiKey;
    }
}
