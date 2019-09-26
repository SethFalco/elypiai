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

package org.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.yugioh.TradingCard;

/**
 * This tells us the subtype of magic or spell {@link TradingCard}
 * this is.
 *
 * @author seth@elypia.org (Syed Shah)
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
    private final String NAME;

    /**
     * Can a spell card have this property.
     */
    private final boolean SPELL;

    /**
     * Can a trap card have this property.
     */
    private final boolean TRAP;

    MagicType(final String name, final boolean spell, final boolean trap) {
        NAME = name;
        SPELL = spell;
        TRAP = trap;
    }

    public String getName() {
        return NAME;
    }

    public boolean isSpell() {
        return SPELL;
    }

    public boolean isTrap() {
        return TRAP;
    }

    public static MagicType get(String name) {
        for (MagicType property : values()) {
            if (property.NAME.equals(name))
                return property;
        }

        return UNKNOWN;
    }
}
