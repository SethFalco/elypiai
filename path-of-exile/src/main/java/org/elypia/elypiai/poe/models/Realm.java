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

package org.elypia.elypiai.poe.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum Realm {

    UNKNOWN("Unknown"),

    @SerializedName("pc")
    PC("PC"),

    @SerializedName("xbox")
    XBOX("Xbox"),

    @SerializedName("sony")
    SONY("Sony");

    private final String NAME;

    Realm(final String NAME) {
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }

    public static Realm get(String name) {
        for (Realm realm : values()) {
            if (realm.NAME.equals(name)) {
                return realm;
            }
        }

        return UNKNOWN;
    }
}
