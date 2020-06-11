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

package org.elypia.elypiai.runescape;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Activity implements Comparable<Activity> {

    /** @see #getDate() */
    @SerializedName("date")
    private Date date;

    /** @see #getDetails() */
    @SerializedName("details")
    private String details;

    /** @see #getText()  */
    @SerializedName("text")
    private String text;

    /** The date that the activity occured. */
    public Date getDate() {
        return date;
    }

    /** A display friendly sentence that represents the event. */
    public String getDetails() {
        return details;
    }

    /** A non-descriptive string that represents the type of event. */
    public String getText() {
        return text;
    }

    @Override
    public int compareTo(Activity o) {
        return date.compareTo(o.date);
    }
}
