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

package org.elypia.elypiai.poe;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public interface PathOfExileService {

    @GET("public-stash-tabs")
    Call<StashTabs> getStashTabs(
        @Query("id") String id
    );

    @GET("leagues")
    Call<List<League>> getLeagues(
        @Query("type") String type,
        @Query("realm") String realm,
        @Query("season") String season,
        @Query("compact") int compact,
        @Query("limit") int limit,
        @Query("offset") int offset
    );

    @GET("leagues/{id}")
    Call<List<League>> getLeagues(
        @Path("id") String id,
        @Query("realm") String realm,
        @Query("ladder") byte ladder,
        @Query("ladderLimit") int limit,
        @Query("ladderOffset") int offset,
        @Query("ladderTrack") int track
    );

    @GET("league-rules/{id}")
    Call<LeagueRule> getLeagueRule(
        @Path("id") String id
    );

    @GET("league-rules")
    Call<List<LeagueRule>> getLeagueRules();

    @GET("ladders/{id}")
    Call<List<LadderEntry>> getLeagueLadder(
        @Path("id") String id,
        @Query("realm") String realm,
        @Query("limit") int limit,
        @Query("offset") int offset,
        @Query("type") String type,
        @Query("track") boolean track,
        @Query("accountName") String accountName,
        @Query("difficulty") Integer difficulty,
        @Query("start") Long start
    );

    @GET("pvp-matches")
    Call<List<PvpMatch>> getPvpMatches(
        @Query("season") String season,
        @Query("realm") String realm
    );
}
