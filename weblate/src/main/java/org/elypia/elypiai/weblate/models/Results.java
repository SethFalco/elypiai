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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 * @param <T> Type of Weblate entity this represents results for.
 * @since 4.2.3
 */
public class Results<T> {

    /**
     * @see #getCount()
     */
    @SerializedName("count")
    private int count;

    /**
     * @see #getNext()
     */
    @SerializedName("next")
    private Object next;

    /**
     * @see #getPrevious()
     */
    @SerializedName("previous")
    private Object previous;

    /**
     * @see #getResults()
     */
    @SerializedName("results")
    private List<T> results;

    /**
     * @return Total item count for object lists.
     */
    public int getCount() {
        return count;
    }

    /**
     * @return Next page URL for object lists.
     */
    public Object getNext() {
        return next;
    }

    /**
     * @return Previous page URL for object lists.
     */
    public Object getPrevious() {
        return previous;
    }

    /**
     * @return Results for object lists.
     */
    public List<T> getResults() {
        return results;
    }
}
