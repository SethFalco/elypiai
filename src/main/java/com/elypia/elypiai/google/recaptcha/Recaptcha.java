package com.elypia.elypiai.google.recaptcha;

import com.elypia.elypiai.utils.okhttp.ElyRequest;

import java.io.IOException;
import java.util.function.Consumer;

public class Recaptcha {

	public static final String ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

	private final String SECRET;

	public Recaptcha(String secret) {
		SECRET = secret;
	}

	public void verifySubmission(String response, Consumer<CaptchaResponse> success, Consumer<IOException> failure) {
		verifySubmission(response, null, success, failure);
	}

	public void verifySubmission(String response, String remoteip, Consumer<CaptchaResponse> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(ENDPOINT);
		req.addParam("secret", SECRET);
		req.addParam("response", response);

		if (remoteip != null)
			req.addParam("remoteip", remoteip);

		req.get(result -> {
			success.accept(new CaptchaResponse(result.asJSONObject()));
		}, err -> {
			failure.accept(err);
		});
	}
}
