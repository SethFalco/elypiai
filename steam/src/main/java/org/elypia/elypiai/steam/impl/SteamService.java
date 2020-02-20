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

package org.elypia.elypiai.steam.impl;

import org.elypia.elypiai.steam.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public interface SteamService {

    @GET("ISteamUser/ResolveVanityURL/v0001/")
    Call<SteamSearch> getSteamId(
        @Query("vanityUrl") String vanityUrl
    );

    @GET("ISteamUser/GetPlayerSummaries/v0002/")
    Call<List<SteamUser>> getUsers(
        @Query("steamids") String steamIds
    );

    @GET("IPlayerService/GetOwnedGames/v0001/")
    Call<List<SteamGame>> getLibrary(
        @Query("steamid") long steamId,
        @Query("include_played_free_games") int freeGames,
        @Query("include_appinfo") int appInfo
    );
}
