package com.elypia.elypiai.google.recaptcha;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Recaptcha {

	public static final String ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

	private final String SECRET;

	public Recaptcha(String secret) {
		SECRET = secret;
	}

	public void verifySubmission(String response, Consumer<CaptchaResponse> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("secret", SECRET);
		queryParams.put("response", response);

		Unirest.get(ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				success.accept(new CaptchaResponse(response.getBody().getObject()));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	public void verifySubmission(String response, String remoteip, Consumer<CaptchaResponse> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("secret", SECRET);
		queryParams.put("response", response);
		queryParams.put("remoteip", remoteip);

		Unirest.get(ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				success.accept(new CaptchaResponse(response.getBody().getObject()));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}
}
