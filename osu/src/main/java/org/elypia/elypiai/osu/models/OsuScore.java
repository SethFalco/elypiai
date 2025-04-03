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

package org.elypia.elypiai.osu.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import org.elypia.retropia.gson.deserializers.BitBooleanDeserializer;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public abstract class OsuScore {

    @SerializedName("score")
    protected long score;

    @SerializedName("maxcombo")
    protected int maxCombo;

    @SerializedName("count50")
    protected int count50;

    @SerializedName("count100")
    protected int count100;

    @SerializedName("count300")
    protected int count300;

    @SerializedName("countmiss")
    protected int countMiss;

    @SerializedName("countkatu")
    protected int countKatu;

    @SerializedName("countgeki")
    protected int countGeki;

    @SerializedName("perfect")
    @JsonAdapter(BitBooleanDeserializer.class)
    protected boolean perfect;

    @SerializedName("user_id")
    protected int userId;

    /**
     * @return Score the user got.
     */
    public long getScore() {
        return score;
    }

    /**
     * @return Largest combo the player received.
     */
    public int getMaxCombo() {
        return maxCombo;
    }

    /**
     * @return Total number of 50s the player hit.
     */
    public int getCount50() {
        return count50;
    }

    /**
     * @return Total number of 100s the player hit.
     */
    public int getCount100() {
        return count100;
    }

    /**
     * @return Total number of 300s the player hit.
     */
    public int getCount300() {
        return count300;
    }

    /**
     * @return Total number of notes missed.
     */
    public int getCountMiss() {
        return countMiss;
    }

    /**
     * @return
     *     Total number of katus in the play. (A set of notes/streams with 100s
     *     or above only.)
     */
    public int getCountKatu() {
        return countKatu;
    }

    /**
     * @return
     *     Total number of gekis in the play. (A set of notes/stream with all
     *     300s.)
     */
    public int getCountGeki() {
        return countGeki;
    }

    /**
     * @return
     *     If the player achieved the maximum combo possible for the beatmap.
     */
    public boolean isPerfect() {
        return perfect;
    }


    /**
     * @return Users id.
     */
    public int getUserId() {
        return userId;
    }
}
