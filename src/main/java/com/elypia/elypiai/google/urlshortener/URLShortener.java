package com.elypia.elypiai.google.urlshortener;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONObject;

import java.io.IOException;
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

	public void shorten(String url, Consumer<String> success, Consumer<IOException> failure) {
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

		ElyRequest req = new ElyRequest(SHORTEN_ENDPOINT);
		req.addParam("key", API_KEY);
		req.addParam("alt", "json");
		req.addParam("prettyPrint", false);
		req.addHeader("Accept-Encoding", "gzip");
		req.addHeader("User-Agent", "Utopiai (gzip)");
		req.addHeader("Content-Type", "application/json");
		req.setFormData(formdata);

		req.post(result -> {
			success.accept(result.asJSONObject().getString("id"));
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * @param url The Google shorted url, to obatin stats of.
	 * @param success What to perform with result.
	 * @param failure What to perform in case of failure, eg timeout.
	 */

	public void shortenedLinkInfo(String url, Consumer<ShortUrlAnalytics> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(SHORTEN_ENDPOINT);
		req.addParam("key", API_KEY);
		req.addParam("alt", "json");
		req.addParam("prettyPrint", false);
		req.addParam("shortUrl", url);
		req.addParam("projection", "FULL");

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			ShortUrlAnalytics stats = object.has("error") ? null : new ShortUrlAnalytics(object) ;
			success.accept(stats);
		}, err -> {
			failure.accept(err);
		});
	}
}
