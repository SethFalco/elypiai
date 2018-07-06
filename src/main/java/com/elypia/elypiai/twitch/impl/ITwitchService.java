package com.elypia.elypiai.twitch.impl;

import com.elypia.elypiai.twitch.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.*;

public interface ITwitchService {

    @GET("users")
    Call<List<TwitchUser>> getUsers(
        @Query("id") List<Integer> ids,
        @Query("login") List<String> logins
    );

    @GET("streams")
    Call<List<TwitchStream>> getStreams(
        @Query("user_id") List<Integer> ids,
        @Query("user_login") List<String> logins,
        @Query("game_id") List<Integer> gameIds,
        @Query("first") int limit,
        @Query("after") String cursor
    );
}
