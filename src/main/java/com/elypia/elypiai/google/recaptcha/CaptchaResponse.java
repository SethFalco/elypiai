package com.elypia.elypiai.google.recaptcha;

import org.json.JSONObject;

public class CaptchaResponse {

	private boolean success;
	private String timestamp;
	private String hostname;
	private String[] errors;

	CaptchaResponse(JSONObject object) {
		success = object.getBoolean("success");
	}

	public boolean isSuccesful() {
		return success;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getHostName() {
		return hostname;
	}

	public String[] getErrorCodes() {
		return errors;
	}
}
