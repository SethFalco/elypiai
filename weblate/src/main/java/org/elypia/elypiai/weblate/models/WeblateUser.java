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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.3
 */
public class WeblateUser {

    /**
     * @see #getEmail()
     */
    @SerializedName("email")
    private String email;

    /**
     * @see #getFullName()
     */
    @SerializedName("fullname")
    private String fullName;

    /**
     * @see #getUsername()
     */
    @SerializedName("username")
    private String username;

    /**
     * @see #isSuperuser()
     */
    @SerializedName("is_superuser")
    private boolean isSuperuser;

    /**
     * @see #isActive()
     */
    @SerializedName("is_active")
    private boolean isActive;

    /**
     * @see #getJoinDate()
     */
    @SerializedName("date_joined")
    private Instant joinDate;

    /**
     * @return User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return User name.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return User's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return If this user is a superuser.
     */
    public boolean isSuperuser() {
        return isSuperuser;
    }

    /**
     * @return If the user is active.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @return Date the user is created.
     */
    public Instant getJoinDate() {
        return joinDate;
    }
}
