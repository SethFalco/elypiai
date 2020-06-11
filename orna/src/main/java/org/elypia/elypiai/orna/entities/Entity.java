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

package org.elypia.elypiai.orna.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Generic class which has what <strong>every</strong>
 * Orna Guide entity holds.
 *
 * This may also be used when an {@link Entity} references
 * another {@link Entity} as a nested property.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class Entity implements Comparable<Entity> {

    /** @see #getId() */
    @SerializedName("id")
    protected int id;

    /** @see #getName() */
    @SerializedName("name")
    protected String name;

    /**
     * @return The internal Orna Guide ID for this entity.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The name of this entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sort all abstract entities by order of ID, this is a small and
     * quick sort to provide items in a consistent order if required.
     *
     * @param o {@link Entity} to compare to.
     * @return A positive value if o is larger, or negative value if o is smaller.
     */
    @Override
    public int compareTo(Entity o) {
        return id - o.id;
    }
}
