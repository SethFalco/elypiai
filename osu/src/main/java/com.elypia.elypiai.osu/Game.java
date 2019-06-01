package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMod;
import com.elypia.elypiai.osu.data.OsuMode;
import com.elypia.elypiai.osu.data.OsuScoreType;
import com.elypia.elypiai.osu.data.OsuTeamType;
import com.elypia.elypiai.osu.deserializers.OsuModDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

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
