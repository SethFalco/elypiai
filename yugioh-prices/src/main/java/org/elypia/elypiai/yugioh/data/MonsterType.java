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

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum MonsterType {

    UNKNOWN("Unknown"),

    @SerializedName("Normal")
    NORMAL("Normal"),

    @SerializedName("Effect")
    EFFECT("Effect"),

    @SerializedName("Fusion")
    FUSION("Fusion"),

    @SerializedName("Ritual")
    RITUAL("Ritual"),

    @SerializedName("Tuner")
    TUNER("Tuner"),

    @SerializedName("Synchro")
    SYNCHRO("Synchro"),

    @SerializedName("Flip")
    FLIP("Flip");

    private final String NAME;

    MonsterType(final String name) {
        NAME = name;
    }

    public static MonsterType get(String name) {
        for (MonsterType type : values()) {
            if (type.NAME.equals(name))
                return type;
        }

        return UNKNOWN;
    }
}
