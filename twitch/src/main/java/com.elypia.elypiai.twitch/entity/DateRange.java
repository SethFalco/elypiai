package com.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class DateRange {

    @SerializedName("started_at")
    private Date start;

    @SerializedName("ended_at")
    private Date end;

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
