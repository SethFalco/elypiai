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

package fun.falco.elypiai.osu;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.QueryParametersInterceptor;
import org.elypia.retropia.gson.deserializers.OffsetTemporalDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import fun.falco.elypiai.osu.deserializers.BeatMapDeserializer;
import fun.falco.elypiai.osu.deserializers.OsuMatchDeserializer;
import fun.falco.elypiai.osu.deserializers.OsuModDeserializer;
import fun.falco.elypiai.osu.deserializers.OsuPlayerDeserializer;
import fun.falco.elypiai.osu.models.BeatMap;
import fun.falco.elypiai.osu.models.Match;
import fun.falco.elypiai.osu.models.OsuId;
import fun.falco.elypiai.osu.models.OsuMod;
import fun.falco.elypiai.osu.models.OsuMode;
import fun.falco.elypiai.osu.models.Player;
import fun.falco.elypiai.osu.models.RecentPlay;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Osu {

    /**
     * The default URL we call too.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://osu.ppy.sh/api/");
        } catch (MalformedURLException ex) {}
    }

    private final String apiKey;
    private final OsuService service;

    /**
     * Creates an osu! object for making calls to the osu API. Using this you
     * can call information on each user, as well as beatmaps, and
     * recent play data for users.
     *
     * @param apiKey API key obtained from the osu! website.
     */
    public Osu(String apiKey) {
        this(apiKey, baseUrl);
    }

    public Osu(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder().addInterceptor(new QueryParametersInterceptor("k", apiKey)).build()
        );
    }

    public Osu(String apiKey, URL baseUrl, OkHttpClient client) {
        this.apiKey = Objects.requireNonNull(apiKey);

        GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(new TypeToken<List<OsuMod>>(){}.getType(), new OsuModDeserializer())
            .registerTypeAdapter(OffsetDateTime.class, new OffsetTemporalDeserializer("yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC));

        Gson gson = gsonBuilder.create();

        gsonBuilder
            .registerTypeAdapter(Player.class, new OsuPlayerDeserializer(gson))
            .registerTypeAdapter(Match.class, new OsuMatchDeserializer(gson))
            .registerTypeAdapter(new TypeToken<List<BeatMap>>(){}.getType(), new BeatMapDeserializer(gson));

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(OsuService.class);
    }

    public Maybe<Player> getPlayer(int id) {
        return getPlayer(id, OsuMode.OSU);
    }

    public Maybe<Player> getPlayer(String username) {
        return getPlayer(username, OsuMode.OSU);
    }

    public Maybe<Player> getPlayer(int id, OsuMode mode) {
        return getPlayer(id, mode, 31);
    }

    public Maybe<Player> getPlayer(String username, OsuMode mode) {
        return getPlayer(username, mode, 31);
    }

    /**
     * Calls the osu! API for the a player by id.
     *
     * @param id Players username.
     * @param mode Game mode to view data for.
     * @param days Number of days to look back for events.
     */
    public Maybe<Player> getPlayer(int id, OsuMode mode, int days) {
        return getPlayer(String.valueOf(id), OsuId.USER_ID, mode, days);
    }

    /**
     * Calls the osu! API for the a player by username.
     *
     * @param username Players username.
     * @param mode Game mode to view data for.
     * @param days Number of days to look back for events.
     */
    public Maybe<Player> getPlayer(String username, OsuMode mode, int days) {
        return getPlayer(username, OsuId.USERNAME, mode, days);
    }

    public Maybe<Player> getPlayer(String username, OsuId type, OsuMode mode, int days) {
        return service.getPlayer(username, type.getType(), mode.getId(), days);
    }

    public Observable<List<BeatMap>> getBeatMaps(int id) {
        return getBeatMaps(id, OsuMode.OSU);
    }

    public Observable<List<BeatMap>> getBeatMaps(int id, OsuMode mode) {
        return getBeatMaps(id, mode, 500);
    }

    /**
     * Get the all BeatMap information associated with a BeatMap id.
     *
     * @param id ID of the beatmap to search grab.
     */
    public Observable<List<BeatMap>> getBeatMaps(int id, OsuMode mode, int limit) {
        return service.getBeatMaps(id, mode.getId(), limit);
    }

    public Observable<List<RecentPlay>> getRecentPlays(int id) {
        return getRecentPlays(id, OsuMode.OSU);
    }

    public Observable<List<RecentPlay>> getRecentPlays(String username) {
        return getRecentPlays(username, OsuMode.OSU);
    }

    public Observable<List<RecentPlay>> getRecentPlays(int id, OsuMode mode) {
        return getRecentPlays(id, mode, 50);
    }

    public Observable<List<RecentPlay>> getRecentPlays(String username, OsuMode mode) {
        return getRecentPlays(username, mode, 50);
    }

    public Observable<List<RecentPlay>> getRecentPlays(int id, OsuMode mode, int limit) {
        return getRecentPlays(String.valueOf(id), OsuId.USER_ID, mode, limit);
    }

    public Observable<List<RecentPlay>> getRecentPlays(String username, OsuMode mode, int limit) {
        return getRecentPlays(username, OsuId.USERNAME, mode, limit);
    }

    public Observable<List<RecentPlay>> getRecentPlays(String user, OsuId type, OsuMode mode, int limit) {
        return service.getRecentPlays(user, type.getType(), mode.getId(), limit);
    }

    public Maybe<Match> getMatch(int id) {
        return service.getMatch(id);
    }

    public String getApiKey() {
        return apiKey;
    }
}
