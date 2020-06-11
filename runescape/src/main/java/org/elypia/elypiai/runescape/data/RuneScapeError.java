/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.runescape.data;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum RuneScapeError {

    /** An error that hasn't been accomodated by this API. */
    UNKNOWN("Unknown"),

    /**
     * RuneScape players that have their account privacy settings
     * set to private.
     */
    PROFILE_PRIVATE("PROFILE_PRIVATE"),

    /** No account exists with that username. */
    NO_PROFILE("NO_PROFILE"),

    /**
     * Accounts that are no longer active. Potentially
     * permanently banned accounts.
     */
    NOT_A_MEMBER("NOT_A_MEMBER");

    private final String name;

    RuneScapeError(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static RuneScapeError get(String name) {
        for (RuneScapeError error : values()) {
            if (error.name.equals(name))
                return error;
        }

        return UNKNOWN;
    }
}
