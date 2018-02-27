package com.elypia.elypiai.sightengine;

import org.json.JSONObject;

public class NudityResponse {

	private boolean success;

	private String requestId;
	private double timestamp;
	private int numberOfOperations;

	// Only non-null when success is true
	private double nudityRaw;
	private double nudityPartial;
	private String partial_tag;
	private double nuditySafe;
	private String mediaId;
	private String mediaUri;

	// Only non-null when success is false.
	private String type;
	private int code;
	private String message;

	NudityResponse(JSONObject object) {
		success = object.getString("status").equals("success");

		JSONObject temp = object.getJSONObject("request");

		requestId = temp.getString("id");
		timestamp = temp.getDouble("timestamp");
		numberOfOperations = temp.getInt("operations");

		if (success) {
			temp = object.getJSONObject("nudity");

			partial_tag   	= temp.optString("partial_tag", null);
			nuditySafe 		= temp.getDouble("safe");
			nudityRaw 		= temp.getDouble("raw");
			nudityPartial	= temp.getDouble("partial");

			temp = object.getJSONObject("media");

			mediaId = temp.getString("id");
			mediaUri = temp.getString("uri");

		} else {
			temp = object.getJSONObject("error");

			type = temp.getString("type");
			code = temp.getInt("code");
			message = temp.getString("message");
		}
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

	public double getTimestamp() {
		return timestamp;
	}

	public int getNumberOfOperations() {
		return numberOfOperations;
	}

	/**
	 * @return	The probability that the image has raw nudity.
	 */

	public double getNudityRawScore() {
		return nudityRaw;
	}

	/**
	 * @return	The probability that the image has partial nudity.
	 */

	public double getNudityPartialScore() {
		return nudityPartial;
	}

	/**
	 * @return	A word associated with what's found in the image.
	 */

	public String getPartialTag() {
		return partial_tag;
	}

	/**
	 * @return	The probability that the image does not contain nudity.
	 */

	public double getNuditySafeScore() {
		return nuditySafe;
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

	public String getErrorType() {
		return type;
	}

	public int getErrorCode() {
		return code;
	}

	public String getErrorMessage() {
		return message;
	}
}