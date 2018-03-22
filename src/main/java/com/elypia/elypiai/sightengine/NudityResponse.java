package com.elypia.elypiai.sightengine;

import org.json.JSONObject;

import java.time.Instant;

public class NudityResponse {

	private SightEngine engine;

	private boolean success;

	private String requestId;
	private Instant timestamp;
	private int operations;

	// Only non-null when success is true
	private double raw;
	private double partial;
	private String tag;
	private double safe;
	private String mediaId;
	private String mediaUri;

	// Only non-null when success is false.
	private SightEngineError error;

	public NudityResponse(SightEngine engine, JSONObject object) {
		this.engine = engine;

		success = object.getString("status").equals("success");

		JSONObject temp = object.getJSONObject("request");

		requestId = temp.getString("id");
		timestamp = Instant.ofEpochSecond(temp.getLong("timestamp"));
		operations = temp.getInt("operations");

		if (!success) {
			temp = object.getJSONObject("error");
			error = new SightEngineError(engine, object.getJSONObject("error"));
			return;
		}

		temp = object.getJSONObject("nudity");

		tag = temp.optString("partial_tag", null);
		safe = temp.getDouble("safe");
		raw = temp.getDouble("raw");
		partial = temp.getDouble("partial");

		temp = object.getJSONObject("media");

		mediaId = temp.getString("id");
		mediaUri = temp.getString("uri");
	}

	public SightEngine getSightEngine() {
		return engine;
	}

	/**
	 * @return	If was succesful request.
	 */

	public boolean isSuccess() {
		return success;
	}

	public String getRequestId() {
		return requestId;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public int getOperations() {
		return operations;
	}

	/**
	 * @return	The probability that the image has raw nudity.
	 */

	public double getRawNudityScore() {
		return raw;
	}

	/**
	 * @return	The probability that the image has partial nudity.
	 */

	public double getNudityPartialScore() {
		return partial;
	}

	/**
	 * @return	A word associated with what's found in the image.
	 */

	public String getPartialTag() {
		return tag;
	}

	/**
	 * @return	The probability that the image does not contain nudity.
	 */

	public double getSafeScore() {
		return safe;
	}

	public boolean isSafe() {
		return isSafe(true);
	}

	public boolean isSafe(boolean allowPartial) {
		if (allowPartial)
			return partial > raw || safe > raw;

		return safe > raw && safe > partial;
	}

	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @return	Link to the url provided to the API for this operation.
	 */

	public String getMediaUrl() {
		return mediaUri;
	}

	public SightEngineError getError() {
		return error;
	}
}
