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

package org.elypia.elypiai.osu;

import com.google.gson.annotations.*;
import org.elypia.elypiai.osu.deserializers.OsuEventDisplayDeseralizer;

import java.util.Date;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class OsuEvent {

    @SerializedName("display_html")
    @JsonAdapter(OsuEventDisplayDeseralizer.class)
    private String message;

    @SerializedName("beatmap_id")
    private int beatmapId;

    @SerializedName("beatmapset_id")
    private int beatmapSetId;

    @SerializedName("date")
    private Date date;

    @SerializedName("epicfactor")
    private int epicFactor;

    public String getMessage() {
        return message;
    }

    public int getBeatmapId() {
        return beatmapId;
    }

    public int getBeatmapSetId() {
        return beatmapSetId;
    }

    public Date getDate() {
        return date;
    }

    public int getEpicFactor() {
        return epicFactor;
    }
}
