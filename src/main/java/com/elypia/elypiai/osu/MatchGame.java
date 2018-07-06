package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.*;
import com.elypia.elypiai.osu.deserializers.OsuModDeserializer;
import com.google.gson.annotations.*;

import java.util.*;

public class MatchGame {

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
    private List<MatchGameScore> scores;

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

    public List<MatchGameScore> getScores() {
        return scores;
    }
}
