/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.mojang;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.mojang.models.MinecraftUser;
import org.elypia.elypiai.mojang.models.Status;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public interface MojangService {

    @POST()
    Single<Status> status();

    @GET("users/profiles/minecraft/{username}")
    Single<MinecraftUser> getUuidAtTime(
        @Path("username") String username,
        @Query("at") Long timestamp
    );

    @GET("user/profiles/{uuid}/names")
    Single<Object> getNameHistory(
        @Path("uuid") String uuid
    );

    /**
     * @param usernames An array of up to 10 usernames.
     * @return The information for all users, omitting users that don't exist.
     */
    @POST("profiles/minecraft")
    Single<List<MinecraftUser>> getUuidsAndExtra(
        @Body Collection<String> usernames
    );

    @POST("user/profile/{uuid}/skin")
    void setSkin(
        @Header("Authorization") String accessToken,
        @Body String payload
    );

    @PUT("user/profile/{uuid}/skin")
    void uploadSkin(
        @Header("Authorization") String accessToken,
        @Part("model") String model,
        @Part("file") String data
    );

    @DELETE("user/profile/{uuid}/skin")
    void deleteSkin(
        @Header("Authorization") String accessToken
    );

    @GET("user/security/location")
    Single<Object> getSecurityStatus(
        @Header("Authorization") String accessToken
    );

    @GET("user/security/challenges")
    Single<Object> getSecurityChallenges(
        @Header("Authorization") String accessToken
    );

    @POST("user/security/location")
    Single<Object> answerSecurityChallenges(
        @Body Object answers
    );

    @POST("orders/statistics")
    Single<Object> getSalesStatistics(
        @Part("metricKeys") String[] keys
    );
}
