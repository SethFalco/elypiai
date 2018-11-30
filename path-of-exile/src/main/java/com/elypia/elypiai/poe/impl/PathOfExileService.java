package com.elypia.elypiai.poe.impl;

import com.elypia.elypiai.poe.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PathOfExileService {

    @GET("public-stash-tabs")
    Call<StashTabs> getStashTabs(
            @Query("id") String id
    );

    @GET("leagues/{id}")
    Call<List<League>> getLeagues(
            @Path("id") String id,
            @Query("type") String type,
            @Query("compact") int compact,
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("league-rules/{id}")
    Call<LeagueRule> getLeagueRule(
            @Path("id") int id
    );

    @GET("league-rules")
    Call<List<LeagueRule>> getLeagueRules();

    @GET("ladders/{id}")
    Call<List<LadderEntry>> getLeagueLadder(
            @Path("id") String id,
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("pvp-matches")
    Call<List<PvPMatch>> getPvPMatches(
            @Query("season") String season
    );
}
