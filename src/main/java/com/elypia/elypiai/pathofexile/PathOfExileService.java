package com.elypia.elypiai.pathofexile;

import retrofit2.*;
import retrofit2.http.*;

import java.util.Collection;

public interface PathOfExileService {

    @GET("public-stash-tabs")
    Call<StashTabs> getStashTabs();

//    @GET("leagues")

    @GET("league-rules")
    Call<Collection<LeagueRule>> getLeagueRules();

    @GET("league-rules")
    Call<LeagueRule> getLeagueRule(@Query("id") int id);

//    @GET("pvp-matches")
}
