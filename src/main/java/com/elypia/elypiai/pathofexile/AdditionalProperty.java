package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

public class AdditionalProperty extends ItemProperty {

	private double progress;

	public AdditionalProperty(PathOfExile poe, JSONObject object) {
		super(poe, object);

		progress = object.getDouble("progress");
	}

	public double getProgress() {
		return progress;
	}
}
