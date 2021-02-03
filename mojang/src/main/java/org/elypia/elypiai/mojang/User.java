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

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

/**
 * @author seth@elypia.org
 * @since 4.3.0
 */
public class User {

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("username")
    private String username;

    @SerializedName("registereIp")
    private String registerIp;

    @SerializedName("migratedFrom")
    private String migratedFrom;

    @SerializedName("migratedAt")
    private Instant migratedAt;

    @SerializedName("registeredAt")
    private Instant registeredAt;

    @SerializedName("passwordChangedAt")
    private Instant passwordChangedAt;

    @SerializedName("dateOfBirth")
    private Instant dateOfBirth;

    @SerializedName("suspended")
    private boolean suspended;

    @SerializedName("blocked")
    private boolean blocked;

    @SerializedName("secured")
    private boolean secured;

    @SerializedName("migrated")
    private boolean migrated;

    @SerializedName("emailVerified")
    private boolean emailVerified;

    @SerializedName("legacyUser")
    private boolean legacyUser;

    @SerializedName("verifiedByParent")
    private boolean verifiedByParent;

    @SerializedName("properties")
    private Map<String, String> properties;

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public String getMigratedFrom() {
        return migratedFrom;
    }

    public Instant getMigratedAt() {
        return migratedAt;
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public Instant getPasswordChangedAt() {
        return passwordChangedAt;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public boolean isSecured() {
        return secured;
    }

    public boolean isMigrated() {
        return migrated;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public boolean isLegacyUser() {
        return legacyUser;
    }

    public boolean isVerifiedByParent() {
        return verifiedByParent;
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(properties);
    }
}
