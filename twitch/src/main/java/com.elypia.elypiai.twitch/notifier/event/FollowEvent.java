package com.elypia.elypiai.twitch.notifier.event;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class FollowEvent {

    @SerializedName("from_id")
    private int followerId;

    @SerializedName("from_name")
    private String followerName;

    @SerializedName("to_id")
    private int followeeId;

    @SerializedName("to_name")
    private String followeeName;

    @SerializedName("followed_at")
    private Date date;

    public int getFollowerId() {
        return followerId;
    }

    public String getFollowerName() {
        return followerName;
    }

    public int getFolloweeId() {
        return followeeId;
    }

    public String getFolloweeName() {
        return followeeName;
    }

    public Date getDate() {
        return date;
    }
}
