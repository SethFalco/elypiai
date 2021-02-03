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

package org.elypia.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class Profile {

    /**
     * @see #getId()
     */
    @SerializedName("id")
    private String id;

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getUserId()
     */
    @SerializedName("userId")
    private String userId;

    /**
     * @see #getCreatedAt()
     */
    @SerializedName("createdAt")
    private Instant createdAt;

    /**
     * @see #isLegacyProfile()
     */
    @SerializedName("legacyProfile")
    private boolean legacyProfile;

    /**
     * @see #isSuspended()
     */
    @SerializedName("suspended")
    private boolean suspended;

    /**
     * @see #isPaid()
     */
    @SerializedName("paid")
    private boolean paid;

    /**
     * @see #isMigrated()
     */
    @SerializedName("migrated")
    private boolean migrated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public boolean isLegacyProfile() {
        return legacyProfile;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isMigrated() {
        return migrated;
    }
}
