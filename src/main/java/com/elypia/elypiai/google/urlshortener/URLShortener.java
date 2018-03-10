package com.elypia.elypiai.google.urlshortener;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class URLShortener {

	private static final String SHORTEN_ENDPOINT = "https://www.googleapis.com/urlshortener/v1/url";

	private final String API_KEY;
	/**
	 * Shortens urls provided using the Google
	 * URL Shortener API.
	 *
	 * @param apiKey API key obtained from Google console.
	 */

	public URLShortener(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * Takes in a url and shortens it.
	 *
	 * @param url URL to shorten.
	 * @param success Method to consume the result.
	 * @param failure Method to consume a failure, eg timeout.
	 */

	public void shorten(String url, Consumer<String> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("alt", "json");
		queryParams.put("prettyPrint", false);

		Map<String, String> headers = new HashMap<>();
		headers.put("Accept-Encoding", "gzip");
		headers.put("User-Agent", "Utopiai (gzip)");
		headers.put("Content-Type", "application/json");

		JSONObject formdata = new JSONObject();
		formdata.put("longUrl", url);

		Unirest.post(SHORTEN_ENDPOINT).queryString(queryParams).headers(headers).body(formdata).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				success.accept(response.getBody().getObject().getString("id"));
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

	/**
	 * @param url The Google shorted url, to obatin stats of.
	 * @param success What to perform with result.
	 * @param failure What to perform in case of failure, eg timeout.
	 */

	public void shortenedLinkInfo(String url, Consumer<ShortUrlAnalytics> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("alt", "json");
		queryParams.put("prettyPrint", false);
		queryParams.put("shortUrl", url);
		queryParams.put("projection", "FULL");

		Unirest.get(SHORTEN_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				ShortUrlAnalytics stats = object.has("error") ? null : new ShortUrlAnalytics(object) ;
				success.accept(stats);
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
