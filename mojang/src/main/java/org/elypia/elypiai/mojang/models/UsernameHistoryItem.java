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

package org.elypia.elypiai.mojang.models;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class UsernameHistoryItem implements Comparable<UsernameHistoryItem> {

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getChangedToAt()
     */
    @SerializedName("changedToAt")
    private Instant changedToAt;

    /**
     * @return The name the user has at the time specified in
     * {@link #getChangedToAt()}, or creating their account with if
     * {@link #getChangedToAt()} is null.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The time this username came into effect, or null
     * if this is the first username the user had.
     */
    public Instant getChangedToAt() {
        return changedToAt;
    }

    @Override
    public int compareTo(UsernameHistoryItem o) {
        return this.changedToAt.compareTo(o.changedToAt);
    }
}
