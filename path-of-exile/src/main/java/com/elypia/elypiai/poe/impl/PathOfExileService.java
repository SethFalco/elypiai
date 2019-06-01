package com.elypia.elypiai.poe.impl;

import com.elypia.elypiai.poe.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

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
