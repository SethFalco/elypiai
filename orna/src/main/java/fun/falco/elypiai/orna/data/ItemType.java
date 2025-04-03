/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.orna.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum ItemType {

    /** Represents a type of item the API might return, isn't known to Elypiai. */
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

    private final String name;

    ItemType(final String name) {
        this.name = name;
    }

    /**
     * @return Clean human readable name for this {@link ItemType}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name Case-sensitive human readable name of the {@link ItemType}.
     * @return Enum value for this type, or {@link #UNKNOWN} if no type is found.
     */
    public static ItemType get(final String name) {
        for (ItemType type : values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }

        return ItemType.UNKNOWN;
    }
}
