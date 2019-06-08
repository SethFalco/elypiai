package com.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

public class ExtensionAnalytics extends Analytics {

    @SerializedName("extension_id")
    private String extensionId;

    public String getExtensionId() {
        return extensionId;
    }
}
