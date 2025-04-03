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

package org.elypia.elypiai.mojang.forms;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.mojang.models.Authorizable;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.3.0
 */
public class RefreshForm extends Authorizable {

    /**
     * @see #getSelectedProfile()
     */
    @SerializedName("selectedProfile")
    private ProfileForm selectedProfile;

    /**
     * @see #isRequestUser()
     */
    @SerializedName("requestUser")
    private boolean requestUser;

    /**
     * @param accessToken
     *     Provided access token will get invalidated once the request has gone
     *     through.
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public ProfileForm getSelectedProfile() {
        return selectedProfile;
    }

    public void setSelectedProfile(ProfileForm selectedProfile) {
        this.selectedProfile = selectedProfile;
    }

    public boolean isRequestUser() {
        return requestUser;
    }

    public void setRequestUser(boolean requestUser) {
        this.requestUser = requestUser;
    }
}
