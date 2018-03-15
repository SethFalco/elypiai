package com.elypia.elypiai.bing;

import com.elypia.elypiai.utils.ElyUtils;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class Bing {

	public static final String GET_SEARCH_ENDPOINT = "https://api.cognitive.microsoft.com/bing/v5.0/search";
	public static final String GET_IMAGE_ENDPOINT = "https://api.cognitive.microsoft.com/bing/v5.0/images/search";

	private final String API_KEY;

	/**
	 * Bing object for making calls for Bing Search API
	 * and Bing Image Search API.
	 *
	 * @param	apiKey		API key obtained from Bing.
	 * @see <a href="https://www.microsoft.com/cognitive-services/en-us/apis">Bing Cognitive Services</a>
	 */

	public Bing(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * Searches Bing and returns the top results url
	 * in the form of a String. <br> <br>
	 * Errors to look out for: <br>
	 * 403 - Rate Limit
	 *
	 * @param	search 		The term to search on Bing.
	 * @param	safeSearch 	Should safeSearch be Strict or off.
	 */

	public void webSearch(String search, boolean safeSearch, boolean random, Consumer<String> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(GET_SEARCH_ENDPOINT);
		req.addParam("q", search);
		req.addParam("safesearch", safeSearch ? "Strict" : "off");
		req.addParam("count", random ? ElyUtils.RANDOM.nextInt(50) + 1 : 1);

		req.addHeader("mkt", "en-us");
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);

		req.get(result -> {
			JSONObject object = result.asJSONObject();

			if (safeSearch && object.has("queryContext")) {
				success.accept("ADULT_INTENT");
				return;
			}

			if (!object.has("webPages")) {
				success.accept("NO_RESULTS");
				return;
			}

			JSONObject webPages = object.getJSONObject("webPages");
			JSONArray values = webPages.getJSONArray("value");
			String link = values.getJSONObject(values.length() - 1).getString("displayUrl");
			success.accept(!link.startsWith("http") ? "http://" + search : search);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Searches Bing images and returns an image url. <br> <br>
	 * Errors to look out for: <br>
	 * 403 - Rate Limit
	 *
	 * @param	search 		The term to search on Bing.
	 * @param	safeSearch 	Should safeSearch be Strict or off.
	 * @param	random		Grab a random result, or first.
	 */

	public void imageSearch(String search, boolean safeSearch, boolean random, Consumer<String> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(GET_SEARCH_ENDPOINT);
		req.addParam("q", search);
		req.addParam("safesearch", safeSearch ? "Strict" : "off");
		req.addParam("count", random ? ElyUtils.RANDOM.nextInt(50) + 1 : 1);
		req.addHeader("mkt", "en-us");
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);

		req.get(result -> {
			JSONObject object = result.asJSONObject();

			if (safeSearch && object.has("queryContext")) {
				success.accept("ADULT_INTENT");
				return;
			}

			JSONArray values = object.getJSONArray("value");

			if (values.length() == 0) {
				success.accept("NO_RESULTS");
				return;
			}

			object = values.getJSONObject(values.length() - 1);

			success.accept(object.getString("contentUrl"));
		}, err -> {
			failure.accept(err);
		});
	}
}
