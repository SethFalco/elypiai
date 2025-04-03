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

package org.elypia.elypiai.weblate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.elypia.elypiai.weblate.models.LanguageStatistics;
import org.elypia.elypiai.weblate.models.Results;
import org.elypia.elypiai.weblate.models.WeblateGroup;
import org.elypia.elypiai.weblate.models.WeblateRole;
import org.elypia.elypiai.weblate.models.WeblateUser;
import org.elypia.retropia.core.HttpClientSingleton;
import org.elypia.retropia.core.interceptors.QueryParametersInterceptor;
import org.elypia.retropia.core.interceptors.TokenAuthorizationInterceptor;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author seth@elypia.org
 * @since 4.2.2
 */
public class Weblate {

    /**
     * Default URL we call to.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://hosted.weblate.org/api/");
        } catch (MalformedURLException ex) {}
    }

    /** The users API key, or null if we're consuming the API through unauthenticated requests. */
    private final String apiKey;

    /** The {@link Retrofit} wrapper around the API. */
    private final WeblateService service;

    /**
     * Creates an instance of the Weblate API for unauthenticated use.
     *
     * @see <a href="https://docs.weblate.org/en/latest/api.html#authentication-and-generic-parameters">Unauthenticated Access to Weblate API</a>
     */
    public Weblate() {
        this(null);
    }

    /**
     * Creates an instance of the Weblate API. You can use this to get
     * information on translation projects on Weblate. This defaults the the
     * baseUrl to the public hosted instance available at
     * {@link #baseUrl https://hosted.weblate.org/api/}.
     *
     * @param apiKey
     *     API key obtained from your Weblate profile page, or null for
     *     unauthenticated access.
     * @see <a href="https://docs.weblate.org/en/latest/api.html#authentication-and-generic-parameters">Unauthenticated Access to Weblate API</a>
     */
    public Weblate(String apiKey) {
        this(apiKey, baseUrl);
    }

    /**
     * Creates an instance of the Weblate API for a given host.
     *
     *
     * @param apiKey API key, or null for unauthenticated access to the API.
     * @param baseUrl Instance base URL, in a format like {@link #baseUrl https://hosted.weblate.org/api/}.
     */
    public Weblate(String apiKey, URL baseUrl) {
        this(
            apiKey,
            baseUrl,
            HttpClientSingleton.getBuilder()
                .addInterceptor(new TokenAuthorizationInterceptor(apiKey))
                .addInterceptor(new QueryParametersInterceptor("format", "json"))
                .build()
        );
    }

    public Weblate(String apiKey, URL baseUrl, OkHttpClient client) {
        this.apiKey = apiKey;
        Objects.requireNonNull(client);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(WeblateService.class);
    }

    public Single<Results<WeblateUser>> getUsers() {
        return service.getUsers();
    }

    public Single<WeblateUser> getUser(final String username) {
        Objects.requireNonNull(username, "Username is required to get single user.");
        return service.getUser(username);
    }

    public Single<Results<WeblateGroup>> getGroups() {
        return service.getGroups();
    }

    public Single<WeblateGroup> getGroup(final int groupId) {
        return service.getGroup(groupId);
    }

    public Single<Results<WeblateRole>> getRoles() {
        return service.getRoles();
    }

    public Single<WeblateRole> getRole(final int roleId) {
        return service.getRole(roleId);
    }

    public Single<List<LanguageStatistics>> getProjectLanguageStatistics(final String projectSlug) {
        Objects.requireNonNull(projectSlug, "Project slug must not be null.");
        return service.getProjectLanguages(projectSlug);
    }
}
