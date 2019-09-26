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

package org.elypia.elypiai.twitch.service;

import org.elypia.elypiai.twitch.TwitchPage;
import org.elypia.elypiai.twitch.entity.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public interface TwitchService {

    @GET("users")
    Call<List<User>> getUsers(
        @Query("id") List<Integer> ids,
        @Query("login") List<String> logins
    );

    @GET("streams")
    Call<TwitchPage<TwitchStream>> getStreams(
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
     * @return A page of webhook subscriptions.
     */
    @GET("webhooks/subscriptions")
    Call<TwitchPage<WebhookSubscription>> getWebhookSubscriptions(
        @Query("after") String after,
        @Query("first") int first
    );
}
