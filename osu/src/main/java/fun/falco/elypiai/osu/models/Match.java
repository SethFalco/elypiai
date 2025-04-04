/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.osu.models;

import java.time.OffsetDateTime;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Match {

    @SerializedName("match_id")
    private int matchId;

    @SerializedName("name")
    private String name;

    @SerializedName("start_time")
    private OffsetDateTime startTime;

    @SerializedName("end_time")
    private OffsetDateTime endTime;

    @SerializedName("games")
    private List<Game> games;

    public int getMatchId() {
        return matchId;
    }

    public String getName() {
        return name;
    }

    public OffsetDateTime getStartTime() {
        return startTime;
    }

    public OffsetDateTime getEndTime() {
        return endTime;
    }

    public List<Game> getGames() {
        return games;
    }
}
