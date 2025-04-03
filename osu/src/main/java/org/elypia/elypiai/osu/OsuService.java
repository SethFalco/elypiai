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

package org.elypia.elypiai.osu;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import org.elypia.elypiai.osu.models.BeatMap;
import org.elypia.elypiai.osu.models.Match;
import org.elypia.elypiai.osu.models.Player;
import org.elypia.elypiai.osu.models.RecentPlay;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public interface OsuService {

    @GET("get_user")
    Maybe<Player> getPlayer(
        @Query("u") String identifier,
        @Query("type") String type,
        @Query("m") int mode,
        @Query("event_days") int days
    );

    @GET("get_beatmaps")
    Observable<List<BeatMap>> getBeatMaps(
        @Query("s") int id,
        @Query("m") int mode,
        @Query("limit") int limit
    );

    @GET("get_user_recent")
    Observable<List<RecentPlay>> getRecentPlays(
        @Query("u") String identifier,
        @Query("type") String type,
        @Query("m") int mode,
        @Query("limit") int limit
    );

    @GET("get_match")
    Maybe<Match> getMatch(
        @Query("mp") int id
    );

    @GET("get_scores")
    Observable<Void> getScores();

    @GET("get_user_best")
    Observable<Void> getPlayersBest();

    @GET("get_replay")
    Observable<Void> getReplay();
}
