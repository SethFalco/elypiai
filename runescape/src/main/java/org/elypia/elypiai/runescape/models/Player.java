/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.runescape.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.elypia.retropia.gson.deserializers.UkFormattedNumberDeserializer;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Player {

    private static final String RANK_URL = "https://services.runescape.com/m=hiscore/compare";
    private static final String AVATAR_URL_FORMAT = "https://secure.runescape.com/m=avatar-rs/%s/chat.png";

    @SerializedName("name")
    private String username;

    @SerializedName("totalskill")
    private int totalLevel;

    @SerializedName("totalxp")
    private long totalXp;

    @SerializedName("questscomplete")
    private int questsComplete;

    @SerializedName("questsstarted")
    private int questsStarted;

    @SerializedName("questsnotstarted")
    private int questsNotStarted;

    @SerializedName("rank")
    @JsonAdapter(UkFormattedNumberDeserializer.class)
    private int rank;

    @SerializedName("combatlevel")
    private int combatLevel;

    @SerializedName("loggedIn")
    private boolean loggedIn;

    @SerializedName("activities")
    private List<Activity> activities;

    @SerializedName("skillvalues")
    private Collection<PlayerStat> stats;

    /**
     * @return Leaderboard ranking URL for this user.
     */
    public String getLeaderboardUrl() {
        String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
        return RANK_URL + "?user1=" + encoded;
    }

    /**
     * @param username Name of a RuneScape user.
     * @return
     *     Leaderboard ranking url for this user compared to the username
     *     provided.
     */
    public String getLeaderboardUrl(String username) {
        if (username.equalsIgnoreCase(this.username)) {
            return getLeaderboardUrl();
        }

        String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
        return getLeaderboardUrl() + "&user2=" + encoded;
    }

    /**
     * @return
     *     URL to the player's avatar, or the default avatar if the player
     *     hasn't set one.
     */
    public String getAvatarUrl() {
        String encoded = URLEncoder.encode(username, StandardCharsets.UTF_8);
        return String.format(AVATAR_URL_FORMAT, encoded);
    }

    /**
     * @return Name of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Total level of the user. (Every skill level added together.)
     */
    public int getTotalLevel() {
        return totalLevel;
    }

    /**
     * @return
     *     Total level with thousand separators formatted as a string using the
     *     system locale.
     */
    public String getTotalLevelFormatted() {
        return getTotalLevelFormatted(Locale.getDefault());
    }

    /**
     * @param locale Locale to format with.
     * @return Total level with thousand separators formatted as a String.
     */
    public String getTotalLevelFormatted(Locale locale) {
        return String.format(locale, "%,d", totalLevel);
    }

    /**
     * @return Total amount of XP the user has.
     */
    public long getTotalXp() {
        return totalXp;
    }

    /**
     * @return Total xp formatted as a String using the default system locale.
     */
    public String getTotalXpFormatted() {
        return getTotalXpFormatted(Locale.getDefault());
    }

    /**
     * @param locale Locale to use when formatting.
     * @return Total XP formatted as a String.
     */
    public String getTotalXpFormatted(Locale locale) {
        return String.format(locale, "%,d", totalXp);
    }

    /**
     * @return Total number of completed quests.
     */
    public int getQuestsComplete() {
        return questsComplete;
    }

    /**
     * @return Total number of quests started but incomplete.
     */
    public int getQuestsStarted() {
        return questsStarted;
    }

    /**
     * @return Total number of quested not even started.
     */
    public int getQuestsNotStarted() {
        return questsNotStarted;
    }

    /**
     * @return Overall leaderboard ranking for this user.
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return Combat level of the player.
     */
    public int getCombatLevel() {
        return combatLevel;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * HashMap of Skills to Stats. Stats containing the players level, XP and
     * rank in the skill.
     *
     * @return Map of skills to stats.
     */
    public Collection<PlayerStat> getStats() {
        return Collections.unmodifiableCollection(stats);
    }

    public PlayerStat getStat(Skill skill) {
        for (PlayerStat stat : stats) {
            if (stat.getSkill() == skill) {
                return stat;
            }
        }

        return null;
    }
}
