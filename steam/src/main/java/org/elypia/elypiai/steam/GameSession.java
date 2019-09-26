/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.steam;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
