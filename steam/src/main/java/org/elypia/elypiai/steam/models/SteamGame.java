/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package org.elypia.elypiai.steam.models;

import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class SteamGame implements Comparable<SteamGame> {

    private static final String GAME_URL_FORMAT = "http://store.steampowered.com/app/%d";
    private static final String IMAGE_FORMAT = "http://media.steampowered.com/steamcommunity/public/images/apps/%s/%s.jpg";

    @SerializedName("appid")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("playtime_forever")
    private long totalPlaytime;

    @SerializedName("playtime_2weeks")
    private long recentPlaytime;

    @SerializedName("img_icon_url")
    private String imgIconUrl;

    @SerializedName("img_logo_url")
    private String imgLogoUrl;

    @SerializedName("has_community_visible_stats")
    private boolean statsVisible;

    public String getUrl() {
        return String.format(GAME_URL_FORMAT, id);
    }

    /**
     * @return App ID of the game.
     */

    public int getId() {
        return id;
    }

    /**
     * @return Name of the game.
     */

    public String getName() {
        return name;
    }

    public long getTotalPlaytime() {
        return getTotalPlaytime(TimeUnit.HOURS);
    }

    public long getTotalPlaytime(TimeUnit unit) {
        return unit.convert(totalPlaytime, TimeUnit.MINUTES);
    }

    public long getRecentPlaytime() {
        return getRecentPlaytime(TimeUnit.HOURS);
    }

    public long getRecentPlaytime(TimeUnit unit) {
        return unit.convert(recentPlaytime, TimeUnit.MINUTES);
    }

    public String getIconUrl() {
        return String.format(IMAGE_FORMAT, id, imgIconUrl);
    }

    public String getLogoUrl() {
        return String.format(IMAGE_FORMAT, id, imgLogoUrl);
    }

    public boolean isStatsVisible() {
        return statsVisible;
    }

    /**
     * Can use Collections#Sort method to sort List of SteamGames. This returns
     * the list in order of recent playtime, and once recent playtime is 0, of
     * total playtime.
     */

    @Override
    public int compareTo(SteamGame game) {
        if (game.recentPlaytime == recentPlaytime) {
            return game.totalPlaytime > totalPlaytime ? 1 : -1;
        }

        return (game.recentPlaytime > recentPlaytime) ? 1 : -1;
    }
}
