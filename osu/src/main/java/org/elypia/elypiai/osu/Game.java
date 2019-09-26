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
import org.elypia.elypiai.osu.data.*;
import org.elypia.elypiai.osu.deserializers.OsuModDeserializer;

import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class Game {

    @SerializedName("game_id")
    private int gameId;

    @SerializedName("start_time")
    private Date startTime;

    @SerializedName("end_time")
    private Date endTime;

    @SerializedName("beatmap_id")
    private int beatmapId;

    @SerializedName("play_mode")
    private OsuMode mode;

    @SerializedName("scoring_type")
    private OsuScoreType scoring;

    @SerializedName("team_type")
    private OsuTeamType teamType;

    @SerializedName("mods")
    @JsonAdapter(OsuModDeserializer.class)
    private List<OsuMod> mods;

    @SerializedName("scores")
    private List<GameScore> scores;

    public int getGameId() {
        return gameId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getBeatmapId() {
        return beatmapId;
    }

    public OsuMode getMode() {
        return mode;
    }

    public OsuScoreType getScoring() {
        return scoring;
    }

    public OsuTeamType getTeamType() {
        return teamType;
    }

    public List<OsuMod> getMods() {
        return mods;
    }

    public List<GameScore> getScores() {
        return scores;
    }
}
