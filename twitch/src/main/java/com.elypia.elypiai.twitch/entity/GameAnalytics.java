package com.elypia.elypiai.twitch.entity;

import com.google.gson.annotations.SerializedName;

public class GameAnalytics extends Analytics {

    @SerializedName("game_id")
    private int gameId;

    public int getGameId() {
        return gameId;
    }
}
