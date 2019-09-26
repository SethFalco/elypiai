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

import java.util.StringJoiner;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public enum Scope {

    UNKNOWN(null),
    ANALYTICS_READ_EXTENSIONS("analytics:read:extensions"),
    ANALYTICS_READ_GAMES("analytics:read:games"),
    BITS_READ("bits:read"),
    CLIPS_EDIT("clips:edit"),
    USER_EDIT("user:edit"),
    USER_EDIT_BROADCAST("user:edit:broadcast"),
    USER_READ_BROADCAST("user:read:broadcast"),
    USER_READ_EMAIL("user:read:email");

    private final String API_NAME;

    Scope(final String API_NAME) {
        this.API_NAME = API_NAME;
    }

    public String getApiName() {
        return API_NAME;
    }

    public static Scope get(String apiName) {
        Scope[] scopes = values();

        for (int i = 1; i < scopes.length; i++) {
            Scope scope = scopes[i];

            if (scope.API_NAME.equals(apiName))
                return scope;
        }

        return UNKNOWN;
    }

    public static String forQuery(Scope... scopes) {
        if (scopes == null)
            return null;

        StringJoiner joiner = new StringJoiner( " ");

        for (Scope scope : scopes)
            joiner.add(scope.API_NAME);

        return joiner.toString();
    }
}
