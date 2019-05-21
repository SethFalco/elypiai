package com.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public abstract class Analytics {

    @SerializedName("type")
    protected Object type;

    @SerializedName("URL")
    protected String url;

    /**
     * Returned by the Twitch API as a date range, we only
     * expose this as {@link DateRange#getStart} and {@link DateRange#getEnd) though.
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
