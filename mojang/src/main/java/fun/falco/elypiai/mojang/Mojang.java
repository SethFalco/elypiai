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

import java.util.Objects;

import org.elypia.retropia.core.HttpClientSingleton;

import okhttp3.OkHttpClient;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class Mojang {

    private final MojangApi mojangApi;
    private final MojangSessionApi mojangSessionApi;

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
    }

    public MojangApi getMojangApi() {
        return mojangApi;
    }

    public MojangSessionApi getMojangSessionApi() {
        return mojangSessionApi;
    }
}
