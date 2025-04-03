/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.runescape;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

import org.elypia.elypiai.runescape.deserializers.PlayerDeserializer;
import org.elypia.elypiai.runescape.models.Player;
import org.elypia.elypiai.runescape.models.QuestStatuses;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.exceptions.FriendlyException;
import org.elypia.retropia.gson.deserializers.TemporalDeserializer;

import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.core.Maybe;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class RuneScape {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://apps.runescape.com/runemetrics/");
        } catch (MalformedURLException ex) {}
    }

    private RuneScapeService service;

    public RuneScape() {
        this(baseUrl);
    }

    public RuneScape(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public RuneScape(URL baseUrl, OkHttpClient client) {
        GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new TemporalDeserializer("dd-MMM-yyyy HH:mm"));

        gsonBuilder.registerTypeAdapter(Player.class, new PlayerDeserializer(gsonBuilder.create()));

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(RuneScapeService.class);
    }

    /**
     * Return the RuneScape player with the username provided. Possible null,
     * if user doesn't exist. If the user does exist it may throw a the consumer
     * may throw a {@link FriendlyException} in the failure consumer.
     *
     * @param username Username of the player to get.
     * @return REST action representing this HTTP request.
     */
    public Maybe<Player> getUser(String username) {
        return service.getUser(username);
    }

    public Maybe<QuestStatuses> getQuestStatuses(String user) {
        return service.getQuestStatuses(user)
            .mapOptional((result) -> result.getQuestStatuses().isEmpty() ? Optional.empty() : Optional.of(result));
    }

    /**
     * Convert XP to the level equivalent.
     *
     * @param xp XP to convert to level.
     * @return Level a player would be with the XP provided.
     */
    public static int getLevelFromXp(long xp) {
        if (xp < 0) {
            throw new IllegalArgumentException("XP can not be of a negative value.");
        }

        int level = 1;
        long result;

        while (xp >= (result = getXpFromLevel(level + 1))) {
            if (result == -1) {
                break;
            }

            level++;
        }

        return level;
    }

    /**
     * Convert a level, or virtual level to the XP equivalent using RuneScapes
     * XP formula.
     *
     * @param level Level to convert to XP.
     * @return XP required to attain this level, or -1 if level is too high.
     */
    public static long getXpFromLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level can not be zero or a negative value.");
        }

        double xp = 0;

        for (int count = 1; count < level; count++) {
            xp += (long) (count + 300 * Math.pow(2, (double) count / 7));

            if (xp >= (double) Long.MAX_VALUE * 4) {
                return -1;
            }
        }

        return (long) (xp / 4);
    }
}
