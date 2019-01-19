package com.elypia.elypiai.twitch.service;

import com.elypia.elypiai.twitch.StreamPage;
import com.elypia.elypiai.twitch.entity.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ITwitchService {

    @GET("users")
    Call<List<User>> getUsers(
        @Query("id") List<Integer> ids,
        @Query("login") List<String> logins
    );

    @GET("streams")
    Call<StreamPage> getStreams(
        @Query("user_id") List<Integer> ids,
        @Query("user_login") List<String> logins,
        @Query("game_id") List<Integer> gameIds,
        @Query("first") int limit,
        @Query("after") String cursor
    );

    @GET("webhooks/subscriptions")
    Call<Object> getWebhookSubscriptions(
        @Header("Authorization: Bearer") String token
    );
}
