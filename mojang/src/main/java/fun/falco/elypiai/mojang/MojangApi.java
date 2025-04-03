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
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

import org.elypia.retropia.core.HttpClientSingleton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fun.falco.elypiai.mojang.deserializers.InstantDeserializer;
import fun.falco.elypiai.mojang.models.Identifiable;
import fun.falco.elypiai.mojang.models.MinecraftUser;
import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Portion of the Mojang API that connects to Mojang's API server.
 *
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class MojangApi {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://api.mojang.com/");
        } catch (MalformedURLException ex) {}
    }

    /** {@link Retrofit} wrapper around the API. */
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
     * @param username Username of the user to get.
     * @return UUID of the Minecraft user that currently has username.
     */
    public Single<MinecraftUser> getUuid(final String username) {
        return service.getUuid(username);
    }

    public Single<Object> getNameHistory(final Identifiable identifiable) {
        return getNameHistory(identifiable.getUuid());
    }

    /**
     * Get all known usernames that the user with this UUID has had.
     *
     * <p>It may not include every name that user has ever had, for example if
     * they requested certain historical usernames be deleted under the right to
     * forget if they contained personal information.</p>
     *
     * @param uuid UUID of the user.
     * @return Previous usernames with timestamps on when the change occurred.
     * @see <a href="https://help.minecraft.net/hc/en-us/articles/360034636712-Minecraft-Usernames">Minecraft Usernames</a>
     */
    public Single<Object> getNameHistory(final UUID uuid) {
        return service.getNameHistory(MinecraftUtils.trimUuid(uuid));
    }

    /**
     * @param usernames Minecraft users to request.
     * @return
     *     UUID and some extra information for all valid users specified. Users
     *     that don't exist will be omitted from the result.
     * @see #getUuids(Collection)
     */
    public Single<List<MinecraftUser>> getUuids(final String[] usernames) {
        return getUuids(List.of(usernames));
    }

    /**
     * Get the UUID and other information for up to 10 users at once.
     * See {@link #getUuidAtTime(String, Instant)} to get only a single user.
     *
     * @param usernames Minecraft users to request.
     * @return
     *     UUID and some extra information for all valid users specified. Users
     *     that don't exist will be omitted from the result.
     */
    public Single<List<MinecraftUser>> getUuids(final Collection<String> usernames) {
        if (usernames.size() == 0) {
            throw new IllegalArgumentException("No usernames specified");
        }

        if (usernames.size() > 10) {
            throw new IllegalArgumentException("Can only request up to 10 usernames at a time");
        }

        if (usernames.stream().anyMatch(Predicate.not(MinecraftUtils::isUsernameValid))) {
            throw new IllegalArgumentException("Username list contains invalid usernames");
        }

        return service.getUuidsAndExtra(usernames);
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
