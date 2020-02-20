/*
 * Copyright 2019-2019 Elypia CIC
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
public enum SkillType {

    UNKNOWN(null),

    @SerializedName("Attack")
    ATTACK("Attack"),

    @SerializedName("Buff")
    BUFF("Buff"),

    /** Damage Over Time */
    @SerializedName("D.O.T.")
    DOT("D.O.T."),

    @SerializedName("Debuff")
    DEBUFF("Debuff"),

    @SerializedName("Magic")
    MAGIC("Magic"),

    @SerializedName("Multi-round Attack")
    MULTI_ROUND_ATTACK("Multi-round Attack"),

    @SerializedName("Multi-round Magic")
    MULTI_ROUND_MAGIC("Multi-round Magic"),

    @SerializedName("Passive")
    PASSIVE("Passive");

    private final String NAME;

    SkillType(final String name) {
        this.NAME = name;
    }

    /**
     * @return The clean human readable name for this {@link SkillType}.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * @param name The case-sensitive human readable name of the {@link SkillType}.
     * @return The enum value for this type, or {@link #UNKNOWN} if no type is found.
     */
    public static SkillType find(final String name) {
        for (SkillType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return SkillType.UNKNOWN;
    }
}
