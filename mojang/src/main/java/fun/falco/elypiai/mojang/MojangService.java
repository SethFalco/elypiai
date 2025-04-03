/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.mojang;

import java.util.Collection;
import java.util.List;

import fun.falco.elypiai.mojang.models.MinecraftUser;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public interface MojangService {

    @GET("users/profiles/minecraft/{username}")
    Single<MinecraftUser> getUuid(
        @Path("username") String username
    );

    @GET("user/profiles/{uuid}/names")
    Single<Object> getNameHistory(
        @Path("uuid") String uuid
    );

    /**
     * @param usernames Array of up to 10 usernames.
     * @return Information for all users, omitting users that don't exist.
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
}
