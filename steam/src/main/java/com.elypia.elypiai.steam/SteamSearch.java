package com.elypia.elypiai.steam;

import com.google.gson.annotations.SerializedName;

public class SteamSearch {

    @SerializedName("steamid")
    private long id;

    @SerializedName("success")
    private int success;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return success == 1;
    }

    public long getId() {
        return id;
    }

    public int getStatus() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
