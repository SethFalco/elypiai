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

package fun.falco.elypiai.osu.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class MapDifficulty {

    @SerializedName("difficultyrating")
    private double display;

    @SerializedName("diff_overall")
    private double overall;

    @SerializedName("diff_size")
    private double size;

    @SerializedName("diff_approach")
    private double approachRate;

    @SerializedName("diff_drain")
    private double healthDrain;

    /**
     * @return Numerical representation of the beatmap difficulty, unrounded.
     */
    public double getDisplay() {
        return display;
    }

    /**
     * @return Overall difficulty (OD) of the beatmap.
     */
    public double getOverall() {
        return overall;
    }

    public double getSize() {
        return size;
    }

    /**
     * @return Approach rate (AR) of the beatmap.
     */
    public double getApproachRate() {
        return approachRate;
    }

    /**
     * @return Health drain (HR) of the beatmap.
     */
    public double getHealthDrain() {
        return healthDrain;
    }
}
