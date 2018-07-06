package com.elypia.elypiai.pathofexile;

import com.google.gson.annotations.SerializedName;

public class AdditionalProperty extends ItemProperty {

	@SerializedName("progress")
	private double progress;

	public double getProgress() {
		return progress;
	}
}
