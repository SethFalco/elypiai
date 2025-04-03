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

package fun.falco.elypiai.runescape.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.SerializedName;

import fun.falco.elypiai.runescape.RuneScape;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class PlayerStat {

    private static final Logger logger = LoggerFactory.getLogger(PlayerStat.class);

    @SerializedName("id")
    private Skill skill;

    @SerializedName("level")
    private int level;

    @SerializedName("xp")
    private int xp;

    @SerializedName("rank")
    private int rank;

    public int getVirtualLevel() {
        if (skill.isElite()) {
            logger.warn("Formula for elite skills is unknown, using regular level formula.");
            return level;
        }

        return RuneScape.getLevelFromXp(getXp());
    }

    /**
     * @return Skill this object contains the stats for.
     */

    public Skill getSkill() {
        return skill;
    }

    /**
     * @return Users current level in this skill.
     */

    public int getLevel() {
        return level;
    }

    /**
     * @return Users current xp in this skill.
     */

    public int getXp() {
        return xp / 10;
    }

    /**
     * @return Users current leaderboard rank for this skill.
     */

    public int getRank() {
        return rank;
    }
}
