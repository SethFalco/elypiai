package com.elypia.elypiai.runescape;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Activity {

    @SerializedName("date")
    private Date date;

    @SerializedName("details")
    private String details;

    @SerializedName("text")
    private String text;

    public Date getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public String getText() {
        return text;
    }
}
