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

package org.elypia.elypiai.mojang;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.mojang.models.MinecraftProfile;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.UUID;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public interface MojangSessionService {

    /**
     * @param uuid UUID of the player.
     * @param unsigned If the response should include a signature.
     * @return Profile information on the player, including textures.
     */
    @GET("session/minecraft/profile/{uuid}")
    Single<MinecraftProfile> getMinecraftProfile(
        @Path("uuid") final UUID uuid,
        @Query("unsigned") final boolean unsigned
    );

    @GET("blockedservers")
    Single<String> getBlockedServers();
}
