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

package org.elypia.elypiai.osu.impl;

import org.elypia.elypiai.osu.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public interface OsuService {

    @GET("get_user")
    Call<Player> getPlayer(
        @Query("u") String identifier,
        @Query("type") String type,
        @Query("m") int mode,
        @Query("event_days") int days
    );

    @GET("get_beatmaps")
    Call<List<BeatMap>> getBeatMaps(
        @Query("s") int id,
        @Query("m") int mode,
        @Query("limit") int limit
    );

    @GET("get_user_recent")
    Call<List<RecentPlay>> getRecentPlays(
        @Query("u") String identifier,
        @Query("type") String type,
        @Query("m") int mode,
        @Query("limit") int limit
    );

    @GET("get_match")
    Call<Match> getMatch(
        @Query("mp") int id
    );

    @GET("get_scores")
    Call<Void> getScores();

    @GET("get_user_best")
    Call<Void> getPlayersBest();

    @GET("get_replay")
    Call<Void> getReplay();
}
