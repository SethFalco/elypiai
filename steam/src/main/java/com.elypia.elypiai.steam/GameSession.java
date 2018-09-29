package com.elypia.elypiai.steam;

import com.google.gson.annotations.SerializedName;

public class GameSession {

    /**
     * <strong>Only non-null if profile is not private.</strong><br>
     * If the user is currently in-game,
     * this value will be returned and set to the gameid of that game.
     */

    @SerializedName("gameid")
    private int gameId;

    /**
     * If the user is currently in-game,
     * this will be the name of the game they are playing.
     * This may be the name of a non-Steam game shortcut.
     */

    @SerializedName("gameextrainfo")
    private String gameStatus;

    @SerializedName("gameserversteamid")
    private long serverId;

    /**
     * <strong>Only non-null if profile is not private.</strong><br>
     * The ip and port of the game server the user is currently playing on,
     * if they are playing on-line in a game using Steam matchmaking.
     */

    @SerializedName("gameserverip")
    private String serverAddress;

    public int getGameId() {
        return gameId;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public long getServerId() {
        return serverId;
    }

    public String getServerAddress() {
        return serverAddress;
    }
}
