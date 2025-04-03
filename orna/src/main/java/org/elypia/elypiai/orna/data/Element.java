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

package org.elypia.elypiai.orna.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum Element {

    UNKNOWN(null),

    @SerializedName("Arcane")
    ARCANE("Arcane"),

    @SerializedName("Dark")
    DARK("Dark"),

    @SerializedName("Dragon")
    DRAGON("Dragon"),

    @SerializedName("Earthen")
    EARTHEN("Earthen"),

    @SerializedName("Fire")
    FIRE("Fire"),

    @SerializedName("Holy")
    HOLY("Holy"),

    @SerializedName("Lightning")
    LIGHTNING("Lightning"),

    @SerializedName("Physical")
    PHYSICAL("Physical"),

    @SerializedName("Water")
    WATER("Water");

    private final String name;

    Element(final String name) {
        this.name = name;
    }

    /**
     * @return Clean human readable name for this {@link Element}.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name Case-sensitive human readable name of the {@link Element}.
     * @return Enum value for this element, or {@link #UNKNOWN} if no type is found.
     */
    public static Element find(final String name) {
        for (Element element : values()) {
            if (element.name.equals(name)) {
                return element;
            }
        }

        return Element.UNKNOWN;
    }
}
