package com.elypia.elypiai.osu;

import com.google.gson.annotations.SerializedName;

public class MapDifficulty {

    @SerializedName("difficultyrating")
    private double display;

    @SerializedName("diff_overall")
    private int overall;

    @SerializedName("diff_size")
    private int size;

    @SerializedName("diff_approach")
    private int approachRate;

    @SerializedName("diff_drain")
    private int healthDrain;

    /**
     * @return 	A numerical representation of the beatmap
     * 			difficulty, unrounded.
     */

    public double getDisplay() {
        return display;
    }

    /**
     * @return 	The overall difficulty (OD) of the beatmap.
     */

    public int getOverall() {
        return overall;
    }

    public int getSize() {
        return size;
    }

    /**
     * @return 	The appraoch rate (AR) of the beatmap.
     */

    public int getApproachRate() {
        return approachRate;
    }

    /**
     * @return 	The health drain (HR) of the beatmap.
     */

    public int getHealthDrain() {
        return healthDrain;
    }
}
