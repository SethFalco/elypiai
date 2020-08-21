/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

package org.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.yugioh.TradingCard;

/**
 * This tells us the subtype of magic or spell {@link TradingCard}
 * this is.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public enum MagicType {

    UNKNOWN("Unknown", false, false),

    @SerializedName("Normal")
    NORMAL("Normal", true, true),

    @SerializedName("Continuous")
    CONTINUOUS("Continous", true, true),

    @SerializedName("Ritual")
    RITUAL("Ritual", true, false),

    @SerializedName("Quick-Play")
    QUICK_PLAY("Quick-Play", true, false),

    @SerializedName("Field")
    FIELD("Field", true, false),

    @SerializedName("Equip")
    EQUIP("Equip", true, false),

    @SerializedName("Counter")
    COUNTER("Counter", false, true);

    /**
     * The name of this property.
     */
    private final String name;

    /**
     * Can a spell card have this property.
     */
    private final boolean forSpell;

    /**
     * Can a trap card have this property.
     */
    private final boolean forTrap;

    MagicType(final String name, final boolean spell, final boolean trap) {
        this.name = name;
        forSpell = spell;
        forTrap = trap;
    }

    public String getName() {
        return name;
    }

    public boolean isSpell() {
        return forSpell;
    }

    public boolean isTrap() {
        return forTrap;
    }

    public static MagicType get(String name) {
        for (MagicType property : values()) {
            if (property.name.equals(name))
                return property;
        }

        return UNKNOWN;
    }
}
