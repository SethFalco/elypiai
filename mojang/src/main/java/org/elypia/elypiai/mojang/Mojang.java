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

import okhttp3.OkHttpClient;
import org.elypia.retropia.core.HttpClientSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class Mojang {

    private static final Logger logger = LoggerFactory.getLogger(Mojang.class);

    private final MojangApi mojangApi;
    private final MojangSessionApi mojangSessionApi;
    private final MojangStatusApi mojangStatusApi;
    private final MojangAuthenticationApi mojangAuthenticationApi;

    /**
     * Creates an instance of the Mojang API.
     */
    public Mojang() {
        this(HttpClientSingleton.getClient());
    }

    public Mojang(OkHttpClient client) {
        Objects.requireNonNull(client);

        mojangApi = new MojangApi(MojangApi.getDefaultBaseUrl(), client);
        mojangSessionApi = new MojangSessionApi(MojangSessionApi.getDefaultBaseUrl(), client);
        mojangStatusApi = new MojangStatusApi(MojangStatusApi.getDefaultBaseUrl(), client);
        mojangAuthenticationApi = new MojangAuthenticationApi(MojangAuthenticationApi.getDefaultBaseUrl(), client);
    }

    public MojangApi getMojangApi() {
        return mojangApi;
    }

    public MojangSessionApi getMojangSessionApi() {
        return mojangSessionApi;
    }

    public MojangStatusApi getMojangStatusApi() {
        return mojangStatusApi;
    }

    public MojangAuthenticationApi getMojangAuthenticationApi() {
        return mojangAuthenticationApi;
    }
}
