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

package org.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public abstract class Analytics {

    @SerializedName("type")
    protected Object type;

    @SerializedName("URL")
    protected String url;

    /**
     * Returned by the Twitch API as a date range, we only
     * expose this as {@link #getStart()} and {@link #getEnd()} though
     * as it didn't seem worth making a {@link com.google.gson.JsonDeserializer}
     * for this.
     */
    @SerializedName("date_range")
    protected DateRange range;

    public Object getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public Date getStart() {
        return range.getStart();
    }

    public Date getEnd() {
        return range.getEnd();
    }
}
