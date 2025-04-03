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

package org.elypia.elypiai.mojang;

import io.reactivex.rxjava3.core.Single;
import org.elypia.elypiai.mojang.forms.AuthenticateForm;
import org.elypia.elypiai.mojang.forms.RefreshForm;
import org.elypia.elypiai.mojang.models.AuthenticatedUser;
import org.elypia.elypiai.mojang.models.Authorizable;
import org.elypia.elypiai.mojang.models.Status;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public interface MojangAuthenticationService {

    /**
     * @return Detailed status of the server.
     */
    @POST()
    Single<Status> status();

    /**
     * @param form Payload to authenticate as a user.
     * @return
     *     New access token for the user, along with the user data if
     *     {@link AuthenticateForm#isRequestUser()} is true.
     */
    @POST("authenticate")
    Single<AuthenticatedUser> authenticate(
        @Body AuthenticateForm form
    );

    @POST("refresh")
    Single<Object> refresh(
        @Body RefreshForm form
    );

    @POST("validate")
    Single<Object> validate(
        @Body Object answers
    );

    @POST("signout")
    Single<Void> signout(
        @Body Object answers
    );

    /**
     * @param authorizable Any type that extends {@link Authorizable}.
     * @return Object to execute or queue the the request.
     */
    @POST("invalidate")
    Single<Void> invalidate(
        @Body Authorizable authorizable
    );
}
