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

package org.elypia.elypiai.twitch.data;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public enum BroadcasterType {

    UNKNOWN(null),

    @SerializedName("partner")
    PARTNER("partner"),

    @SerializedName("affiliate")
    AFFILIATE("affiliate"),

    @SerializedName("")
    NORMAL("");

    private final String API_NAME;

    BroadcasterType(final String name) {
        API_NAME = name;
    }

    public String getName() {
        return API_NAME;
    }

    public static BroadcasterType get(String name) {
        BroadcasterType[] types = values();

        for (int i = 1; i < types.length; i++) {
            BroadcasterType type = types[i];

            if (type.API_NAME.equals(name))
                return type;
        }

        return UNKNOWN;
    }
}
