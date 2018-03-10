package com.elypia.elypiai.sightengine;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SightEngine {

	public static final String NUDITY_ENDPOINT = "https://api.sightengine.com/1.0/nudity.json";

	private final String USER;
	private final String SECRET;

	/**
	 * The SigthtEngine API can detect nudity in images
	 * provided, as well as has facial recognistion, mood
	 * recognition and other functions.
	 *
	 * @param user		The user name from the SightEnginer API.
	 * @param secret	The Secret from the SightEngine API.
	 */

	public SightEngine(String user, String secret) {
		USER = user;
		SECRET = secret;
	}

	/**
	 * URL should be of an image, API checks the image
	 * and returns information on it, such as if the image is safe
	 * with a score from 0 to 1, of how safe it is, tags of what
	 * makes it unsafe.
	 *
	 * @param 	url		The url of an image to check for.
	 */

	public void nudityDetection(String url, Consumer<NudityResponse> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("api_user", USER);
		queryParams.put("api_secret", SECRET);
		queryParams.put("url", url);

		Unirest.get(NUDITY_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				success.accept(new NudityResponse(object));
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
