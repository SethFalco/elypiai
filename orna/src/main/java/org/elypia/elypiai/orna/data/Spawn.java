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

package org.elypia.elypiai.orna.data;

import com.google.gson.annotations.SerializedName;

/**
 * Types of spawns monsters can have, this may define if it only spawns
 * during a certain time or during certain events.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public enum Spawn {

    UNKNOWN(null),

    @SerializedName("Day Only")
    DAY_ONLY("Day Only"),

    @SerializedName("Gauntlet Only")
    GAUNTLET_ONLY("Gauntlet Only"),

    @SerializedName("Night Only")
    NIGHT_ONLY("Night Only"),

    @SerializedName("Raid")
    RAID("Raid"),

    @SerializedName("Water Only")
    WATER_ONLY("Water Only");

    private final String name;

    Spawn(final String name) {
        this.name = name;
    }

    /**
     * @return The clean human readable name for this spawn type.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The case-sensitive human readable name of the {@link Spawn}.
     * @return The enum value for this spawn, or {@link #UNKNOWN} if no type is found.
     */
    public static Spawn find(final String name) {
        for (Spawn spawn : values()) {
            if (spawn.name.equals(name))
                return spawn;
        }

        return Spawn.UNKNOWN;
    }
}
