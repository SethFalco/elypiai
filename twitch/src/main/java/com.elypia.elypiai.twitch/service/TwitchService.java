package com.elypia.elypiai.twitch.service;

import com.elypia.elypiai.twitch.TwitchPage;
import com.elypia.elypiai.twitch.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TwitchService {

    @GET("users")
    Call<List<User>> getUsers(
        @Query("id") List<Integer> ids,
        @Query("login") List<String> logins
    );

    @GET("streams")
    Call<TwitchPage<Stream>> getStreams(
        @Query("user_id") List<Integer> ids,
        @Query("user_login") List<String> logins,
        @Query("game_id") List<Integer> gameIds,
        @Query("first") int limit,
        @Query("after") String cursor
    );

    @POST("webhooks/hub")
    Call<Void> updateWebhookSubscription(
        @Query("hub.callback") String callback,
        @Query("hub.mode") String mode,
        @Query("hub.topic") String topic,
        @Query("hub.lease_seconds") int seconds,
        @Query("hub.secret") String secret
    );

    /**
     * @param after Cursor for forward pagination: tells the server
     *              where to start fetching the next set of results,
     *              in a multi-page response. The cursor value specified
     *              here is from the pagination response field of a prior query.
     * @param first Number of values to be returned per page. Limit: 100. Default: 20.
     * @return
     */
    @GET("webhooks/subscriptions")
    Call<TwitchPage<WebhookSubscription>> getWebhookSubscriptions(
        @Query("after") String after,
        @Query("first") int first
    );
}
