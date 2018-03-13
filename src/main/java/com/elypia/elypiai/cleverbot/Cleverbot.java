package com.elypia.elypiai.cleverbot;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Cleverbot {

	public static final String GET_REPLY_ENDPOINT = "https://www.cleverbot.com/getreply";

	private final String API_KEY;

	private Map<String, CleverResponse> cache;

	/**
	 * Creates the Cleverbot object in order to make requests
	 * to the cleverbot API, apikey can be obtained from
	 * cleverbot website for free.
	 *
	 * @param	apiKey 	API key recieved upon signing up.
	 * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
	 */

	public Cleverbot(String apiKey) {
		API_KEY = apiKey;
		cache = new HashMap<>();
	}

	public void say(String input, Consumer<CleverResponse> result, Consumer<IOException> failure) {
		say(input, null, result, failure);
	}

	/**
	 * Returns the cleverbot response to the given input, however allows
	 * the user to store and pass their CS value themself rather than rely
	 * on the Map used internally in this API, encouraged for longer
	 * conversations you may wish to continue after retarting the application.
	 *
	 * @param cs		The Cleverbot state from previously.
	 * @param input		The text to send to cleverbot.
	 */

	public void say(String input, String cs, Consumer<CleverResponse> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(GET_REPLY_ENDPOINT);
		req.addParam("key", API_KEY);
		req.addParam("wrapper", "Elypiai");
		req.addParam("input", input);

		if (cs != null)
			req.addParam("cs", cs);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			CleverResponse response = new CleverResponse(object);

			success.accept(response);

			if (cs != null)
				cache.remove(cs);

			cache.put(response.getCS(), response);
		}, err -> {
			failure.accept(err);
		});
	}

	public CleverResponse getCleverResponse(String cs) {
		if (cs == null)
			return null;

		return cache.get(cs);
	}

	public String getHistoryScript(String cs) {
		CleverResponse response = getCleverResponse(cs);

		if (response != null)
			return response.getHistoryScript();
		else
			return null;
	}
}
