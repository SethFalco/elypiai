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

package fun.falco.elypiai.poe.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum AscendancyType {

    UNKNOWN("Unknown"),

    @SerializedName("Dualist")
    DUELIST("Dualist"),

    @SerializedName("Shadow")
    SHADOW("Shadow"),

    @SerializedName("Marauder")
    MARAUDER("Marauder"),

    @SerializedName("Witch")
    WITCH("Witch"),

    @SerializedName("Ranger")
    RANGER("Ranger"),

    @SerializedName("Templar")
    TEMPLAR("Templar"),

    @SerializedName("Scion")
    SCION("Scion");

    private final String NAME;

    AscendancyType(String name) {
        NAME = name;
    }

    public static AscendancyType get(String name) {
        for (AscendancyType ascendancy : values()) {
            if (ascendancy.NAME.equals(name)) {
                return ascendancy;
            }
        }

        return UNKNOWN;
    }
}
