package com.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class WebhookSubscription {

    /** The topic used in the initial subscription. */
    @SerializedName("topic")
    private String topic;

    /** The callback provided for this subscription. */
    @SerializedName("callback")
    private String callback;

    /** Date and time when this subscription expires. */
    @SerializedName("expires_at")
    private Date expires;

    public String getTopic() {
        return topic;
    }

    public String getCallback() {
        return callback;
    }

    public Date getExpires() {
        return expires;
    }
}
