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

package org.elypia.elypiai.mojang;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

import org.elypia.elypiai.mojang.deserializers.InstantDeserializer;
import org.elypia.elypiai.mojang.models.Identifiable;
import org.elypia.elypiai.mojang.models.MinecraftUser;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * The portion of the Mojang API that connects to the
 * {@link org.elypia.elypiai.mojang.models.MojangServer#API_MOJANG API server}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MojangApi {

    private static final Logger logger = LoggerFactory.getLogger(MojangApi.class);

    /**
     * <p>The default URL we call too.</p>
     * <p>Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.</p>
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://api.mojang.com/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    /** The {@link Retrofit} wrapper around the API. */
    private final MojangService service;

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangApi() {
        this(baseUrl);
    }

    /**
     * Creates an instance of the Mojang API.
     */
    public MojangApi(URL baseUrl) {
        this(baseUrl, HttpClientSingleton.getClient());
    }

    public MojangApi(URL baseUrl, OkHttpClient client) {
        Objects.requireNonNull(baseUrl);
        Objects.requireNonNull(client);

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantDeserializer())
            .create();

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(MojangService.class);
    }

    /**
     * Calls {@link #getUuidAtTime(String, Instant)} with the timestamp set to null.
     *
     * @param username The username of the user to get.
     * @return The Minecraft user that had the username at the time, or the username
     * currently if no timestamp was specified.
     * @see #getUuidAtTime(String, Instant)
     */
    public Single<MinecraftUser> getUuid(final String username) {
        return service.getUuidAtTime(username, null);
    }

    /**
     * @param username The username of the user to get.
     * @param timestamp A timestamp to control at what time they had this username.
     * @return The Minecraft user that had the username at the time, or the username
     * currently if no timestamp was specified.
     */
    public Single<MinecraftUser> getUuidAtTime(final String username, final Instant timestamp) {
        final Long at = (timestamp != null) ? timestamp.getEpochSecond() : null;
        return service.getUuidAtTime(username, at);
    }

    public Single<Object> getNameHistory(final Identifiable identifiable) {
        return getNameHistory(identifiable.getUuid());
    }

    /**
     * <p>Get all known usernames that the user with this UUID has had.</p>
     *
     * <p>
     *  It may not include every name that user has ever had, for example if they
     *  requested certain historical usernames be deleted under the right to forget
     *  if they contained personal information.
     * </p>
     *
     * @param uuid The UUID of the user.
     * @return A list of previous usernames with timestamps on when the change occurred.
     * @see <a href="https://help.minecraft.net/hc/en-us/articles/360034636712-Minecraft-Usernames">Minecraft Usernames</a>
     */
    public Single<Object> getNameHistory(final UUID uuid) {
        return service.getNameHistory(MinecraftUtils.trimUuid(uuid));
    }

    /**
     * @param usernames The minecraft users to request.
     * @return The UUID and some extra information for all valid users specified.
     * Users that don't exist will be omitted from the result.
     * @see #getUuids(Collection)
     */
    public Single<List<MinecraftUser>> getUuids(final String[] usernames) {
        return getUuids(List.of(usernames));
    }

    /**
     * Get the UUID and other information for up to 10 users at once.
     * See {@link #getUuidAtTime(String, Instant)} to get only a single user.
     *
     * @param usernames The minecraft users to request.
     * @return The UUID and some extra information for all valid users specified.
     * Users that don't exist will be omitted from the result.
     */
    public Single<List<MinecraftUser>> getUuids(final Collection<String> usernames) {
        if (usernames.size() == 0)
            throw new IllegalArgumentException("No usernames specified");

        if (usernames.size() > 10)
            throw new IllegalArgumentException("Can only request up to 10 usernames at a time");

        if (usernames.stream().anyMatch(Predicate.not(MinecraftUtils::isUsernameValid)))
            throw new IllegalArgumentException("Username list contains invalid usernames");

        return service.getUuidsAndExtra(usernames);
    }

    /**
     * @return The default base URL. This may not be the same as the base URL
     * that was passed to this class on construction.
     */
    public static URL getDefaultBaseUrl() {
        return baseUrl;
    }
}
