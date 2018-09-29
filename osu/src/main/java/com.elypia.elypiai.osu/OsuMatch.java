package com.elypia.elypiai.osu;

import com.elypia.elypiai.utils.gson.deserializers.UtcDateDeserializer;
import com.google.gson.annotations.*;

import java.util.*;

public class OsuMatch {

    @SerializedName("match_id")
    private int matchId;

    @SerializedName("name")
    private String name;

    @SerializedName("start_time")
    @JsonAdapter(UtcDateDeserializer.class)
    private Date startTime;

    @SerializedName("end_time")
    @JsonAdapter(UtcDateDeserializer.class)
    private Date endTime;

    @SerializedName("games")
    private List<MatchGame> games;

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

    public List<MatchGame> getGames() {
        return games;
    }
}
