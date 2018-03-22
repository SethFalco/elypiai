package com.elypia.elypiai.sightengine;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class SightEngine {

	public static final String NUDITY_ENDPOINT = "https://api.sightengine.com/1.0/nudity.json";

	private SightEngine engine;

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
		engine = this;
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

	public void nudityDetection(String url, Consumer<NudityResponse> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(NUDITY_ENDPOINT);
		req.addParam("api_user", USER);
		req.addParam("api_secret", SECRET);
		req.addParam("url", url);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			NudityResponse nudity = new NudityResponse(engine, object);

			success.accept(nudity);
		}, failure);
	}
}
