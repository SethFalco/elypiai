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

import java.util.UUID;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class MinecraftUser {

    /**
     * @see #getUuid()
     */
    @SerializedName("id")
    private UUID uuid;

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #isLegacy
     */
    @SerializedName("legacy")
    private boolean isLegacy;

    /**
     * @see #isDemo()
     */
    @SerializedName("demo")
    private boolean isDemo;

    /**
     * @return The accounts UUID.
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * @return The username of the account.
     */
    public String getName() {
        return name;
    }

    /**
     * @return If the account hasn't migrated to a Mojang account.
     */
    public boolean isLegacy() {
        return isLegacy;
    }

    /**
     * @return If this is an unpaid account.
     */
    public boolean isDemo() {
        return isDemo;
    }
}
