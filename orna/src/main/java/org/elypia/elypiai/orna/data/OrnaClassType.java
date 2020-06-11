/*
 * Copyright 2019-2020 Elypia CIC
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
 * @author seth@elypia.org (Seth Falco)
 */
public enum OrnaClassType {

    UNKNOWN(null),

    @SerializedName("Mage")
    MAGE("Mage"),

    @SerializedName("Thief")
    THIEF("Thief"),

    /** Damage Over Time */
    @SerializedName("Warrior")
    WARRIOR("Warrior");

    private final String name;

    OrnaClassType(final String name) {
        this.name = name;
    }

    /**
     * @return The clean human readable name for this {@link OrnaClassType}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The case-sensitive human readable name of the {@link OrnaClassType}.
     * @return The enum value for this class, or {@link #UNKNOWN} if no class is found.
     */
    public static OrnaClassType find(final String name) {
        for (OrnaClassType type : values()) {
            if (type.name.equals(name))
                return type;
        }

        return OrnaClassType.UNKNOWN;
    }
}
