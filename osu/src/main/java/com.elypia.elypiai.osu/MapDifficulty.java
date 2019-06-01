package com.elypia.elypiai.osu;

import com.google.gson.annotations.SerializedName;

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
     * @return 	A numerical representation of the beatmap
     * 			difficulty, unrounded.
     */
    public double getDisplay() {
        return display;
    }

    /**
     * @return 	The overall difficulty (OD) of the beatmap.
     */
    public double getOverall() {
        return overall;
    }

    public double getSize() {
        return size;
    }

    /**
     * @return 	The appraoch rate (AR) of the beatmap.
     */
    public double getApproachRate() {
        return approachRate;
    }

    /**
     * @return 	The health drain (HR) of the beatmap.
     */
    public double getHealthDrain() {
        return healthDrain;
    }
}
