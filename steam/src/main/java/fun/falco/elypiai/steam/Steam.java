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

package fun.falco.elypiai.steam;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.QueryParametersInterceptor;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import fun.falco.elypiai.steam.deserializers.SteamGameDeserializer;
import fun.falco.elypiai.steam.deserializers.SteamSearchDeserializer;
import fun.falco.elypiai.steam.deserializers.SteamUserDeserializer;
import fun.falco.elypiai.steam.models.SteamGame;
import fun.falco.elypiai.steam.models.SteamSearch;
import fun.falco.elypiai.steam.models.SteamUser;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Steam {

    /** Regular expression that matches against profile URLs and returns the username or ID. */
    private static final Pattern VANITY_URL = Pattern.compile("^(?:https?://)?steamcommunity\\.com/id/([^/]+)/?$");

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("http://api.steampowered.com/");
        } catch (MalformedURLException ex) {}
    }

    private final String apiKey;
    private final SteamService service;

    /**
     * Steam API allows calls to basic Steam information as well as user
     * information such as query the inventory or obtaining stats.
     *
     * @param apiKey API key obtained from Steam.
     */

    public Steam(String apiKey) {
        this(apiKey, baseUrl);
    }

    public Steam(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder().addInterceptor(new QueryParametersInterceptor("key", apiKey)).build()
        );
    }

    public Steam(String apiKey, URL url, OkHttpClient client) {
        this.apiKey = Objects.requireNonNull(apiKey);

        GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(SteamSearch.class, new SteamSearchDeserializer())
            .registerTypeAdapter(new TypeToken<List<SteamGame>>(){}.getType(), new SteamGameDeserializer())
            .registerTypeAdapter(new TypeToken<List<SteamUser>>(){}.getType(), new SteamUserDeserializer(this));

        service = new Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(SteamService.class);
    }

    /**
     * @param vanityUrl Custom URL entirely, or custom URL route after ID.
     * @return Search result for if a user was found or not.
     */
    public Single<SteamSearch> getIdFromVanityUrl(String vanityUrl) {
        String name = getUsernameFromUrl(vanityUrl);
        return service.getSteamId((name == null) ? vanityUrl : name);
    }

    /**
     * Gets a list of steam players that are returned based on the IDs
     * provided.
     *
     * @param ids IDs of all steam players to return.
     * @return REST action which will return the results, never null.
     */
    public Single<List<SteamUser>> getUsers(long... ids) {
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("Must specify at least one user to fetch.");
        }

        StringJoiner joiner = new StringJoiner(",");

        for (long id : ids) {
            joiner.add(String.valueOf(id));
        }

        return service.getUsers(joiner.toString());
    }

    /**
     * Gets the Steam users Library, do note the first time the method is called
     * for each SteamProfile; it consumes another API call, the Library is
     * cached however upon method call. This contains a list of games the Steam
     * user owns (or has played, for free games) sorted from by recent playtime,
     * and when RecentPlaytime is 0, by total playtime.
     *
     * @param id Steam user to obtain library for.
     * @return REST action which will return a list of steam games.
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
     * @param vanityUrl URL to a users stream profile.
     * @return
     *     Identifying portion of the URL which represents the users custom URL.
     */
    public static String getUsernameFromUrl(String vanityUrl) {
        Matcher matcher = VANITY_URL.matcher(vanityUrl);
        return (matcher.find()) ? matcher.group(1) : null;
    }

    public String getApiKey() {
        return apiKey;
    }
}
