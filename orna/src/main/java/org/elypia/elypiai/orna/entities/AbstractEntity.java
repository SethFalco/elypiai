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

package org.elypia.elypiai.orna.entities;

/**
 * Generic class which has what <strong>every</strong>
 * Orna Guide entity holds.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class AbstractEntity implements Comparable<AbstractEntity> {

    /** The internal Orna Guide ID for this entity. */
    protected int id;

    /** The name of this entity. */
    protected String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Sort all abstract entities by order of ID, this is a small and
     * quick sort to provide items in a consistent order if required.
     *
     * @param o {@link AbstractEntity} to compare to.
     * @return A positive value if o is larger, or negative value if o is smaller.
     */
    @Override
    public int compareTo(AbstractEntity o) {
        return id - o.id;
    }
}
