package com.elypia.elypiai.osu.impl;

import com.elypia.elypiai.osu.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IOsuService {

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
