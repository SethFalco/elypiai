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
 * @author seth@elypia.org (Syed Shah)
 */
public enum ItemType {

    UNKNOWN(null),

    @SerializedName("Curative")
    CURATIVE("Curative"),

    @SerializedName("Weapon")
    WEAPON("Weapon"),

    @SerializedName("Head")
    HEAD("Head"),

    @SerializedName("Armor")
    ARMOR("Armor"),

    @SerializedName("Legs")
    LEGS("Legs"),

    @SerializedName("Accessory")
    ACCESSORY("Accessory"),

    @SerializedName("Material")
    MATERIAL("Material"),

    @SerializedName("Item")
    ITEM("Item"),

    @SerializedName("Other")
    OTHER("Other");

    private final String NAME;

    ItemType(final String name) {
        this.NAME = name;
    }

    /**
     * @return The clean human readable name for this {@link ItemType}.
     */
    public String getName() {
        return this.NAME;
    }

    /**
     * @param name The case-sensitive human readable name of the {@link ItemType}.
     * @return The enum value for this type, or {@link #UNKNOWN} if no type is found.
     */
    public static ItemType find(final String name) {
        for (ItemType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return ItemType.UNKNOWN;
    }
}
