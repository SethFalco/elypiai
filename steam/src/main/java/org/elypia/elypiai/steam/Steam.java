/*
 * Copyright 2019-2019 Elypia CIC
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
import okhttp3.*;
import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ext.*;
import org.elypia.elypiai.steam.deserializers.*;
import org.elypia.elypiai.steam.impl.SteamService;
import org.slf4j.*;
import retrofit2.Call;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.*;
import java.util.*;
import java.util.regex.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Steam extends ApiWrapper {

    /** A regular expression that matches against profile urls and returns the username or id. */
    private static final Pattern VANITY_URL = Pattern.compile("^(?:https?://)?steamcommunity\\.com/id/([^/]+)/?$");

    private static final Logger logger = LoggerFactory.getLogger(Steam.class);

    /**
     * The default URL we call too. <br>
     * Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.
     */
    private static URL BASE_URL;

    static {
        try {
            BASE_URL = new URL("http://api.steampowered.com/");
        } catch (MalformedURLException ex) {
            logger.error(Elypiai.MALFORMED, ex);
        }
    }

    private final String API_KEY;
    private final SteamService service;

    /**
     * The Steam API allows calls to basic Steam information
     * as well as user information such as query the inventory
     * or obtaining stats.
     *
     * @param apiKey API key obtained from Steam.
     * @param exts Extensions to add to this wrapper.
     */

    public Steam(String apiKey, WrapperExtension... exts) {
        this(BASE_URL, apiKey, exts);
    }

    public Steam(URL baseUrl, String apiKey, WrapperExtension... exts) {
        super(exts);
        API_KEY = Objects.requireNonNull(apiKey);

        OkHttpClient client = RequestService.getBuilder()
            .addInterceptor((chain) -> {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("key", apiKey).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            })
            .addInterceptor(new ExtensionInterceptor(this))
            .build();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(SteamSearch.class, new SteamSearchDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamGame>>(){}.getType(), new SteamGameDeserializer())
                .registerTypeAdapter(new TypeToken<List<SteamUser>>(){}.getType(), new SteamUserDeserializer(this));

        service = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build()
                .create(SteamService.class);
    }

    /**
     * @param vanityUrl The custom url entirely, or custom url route after id.
     * @return The search result of if a user was found or not.
     */
    public RestAction<SteamSearch> getIdFromVanityUrl(String vanityUrl) {
        String name = getUsernameFromUrl(vanityUrl);
        Call<SteamSearch> call = service.getSteamId((name == null) ? vanityUrl : name);
        return new RestAction<>(call);
    }

    public RestAction<List<SteamUser>> getUsers(long...ids) {
        if (ids == null || ids.length == 0)
            throw new IllegalArgumentException("Must specify at least one user to fetch.");

        StringJoiner joiner = new StringJoiner(",");

        for (long id : ids)
            joiner.add(String.valueOf(id));

        Call<List<SteamUser>> call = service.getUsers(joiner.toString());
        return new RestAction<>(call);
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
    public RestAction<List<SteamGame>> getLibrary(long id) {
        return getLibrary(id, true);
    }

    public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames) {
        return getLibrary(id, freeGames, true);
    }

    public RestAction<List<SteamGame>> getLibrary(long id, boolean freeGames, boolean info) {
        Call<List<SteamGame>> call = service.getLibrary(id, freeGames ? 1 : 0, info ? 1 : 0);
        return new RestAction<>(call);
    }

    public static String getUsernameFromUrl(String vanityUrl) {
        Matcher matcher = VANITY_URL.matcher(vanityUrl);
        return (matcher.find()) ? matcher.group(1) : null;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
