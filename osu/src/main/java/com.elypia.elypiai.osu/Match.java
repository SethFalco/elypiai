package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.deserializers.OsuDateDeserializer;
import com.google.gson.annotations.*;

import java.util.*;

public class Match {

    @SerializedName("match_id")
    private int matchId;

    @SerializedName("name")
    private String name;

    @SerializedName("start_time")
    @JsonAdapter(OsuDateDeserializer.class)
    private Date startTime;

    @SerializedName("end_time")
    @JsonAdapter(OsuDateDeserializer.class)
    private Date endTime;

    @SerializedName("games")
    private List<Game> games;

    public int getMatchId() {
        return matchId;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public List<Game> getGames() {
        return games;
    }
}
