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

package org.elypia.elypiai.poe;

import com.google.gson.annotations.*;
import org.elypia.retropia.gson.deserializers.*;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Guild {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("tag")
    private String tag;

    @SerializedName("statusMessage")
    @JsonAdapter(EmptyNullDeserializer.class)
    private String status;

    @SerializedName("createdAt")
    @JsonAdapter(IsoDateTimeTemporalDeserializer.class)
    private Instant creationDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }
}
