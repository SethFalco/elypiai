package com.elypia.elypiai.sightengine;

import com.google.gson.annotations.SerializedName;

public class NudityScore {

	@SerializedName("raw")
	private double raw;

	@SerializedName("partial")
	private double partial;

	@SerializedName("safe")
	private double safe;

	@SerializedName("partial_tag")
	private String tag;

	public boolean isSafe() {
		return isSafe(true);
	}

	public boolean isSafe(boolean allowPartial) {
		if (allowPartial)
			return partial > raw || safe > raw;

		return safe > raw && safe > partial;
	}

	/**
	 * @return	The probability that the image has raw nudity.
	 */

	public double getRaw() {
		return raw;
	}

	/**
	 * @return	The probability that the image has partial nudity.
	 */

	public double getPartial() {
		return partial;
	}

	/**
	 * @return	A word associated with what's found in the image.
	 */

	public String getPartialTags() {
		return tag;
	}

	/**
	 * @return	The probability that the image does not contain nudity.
	 */

	public double getSafe() {
		return safe;
	}
}
