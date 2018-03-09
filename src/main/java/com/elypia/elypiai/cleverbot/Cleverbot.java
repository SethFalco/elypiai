package com.elypia.elypiai.cleverbot;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

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

	public void say(String input, Consumer<CleverResponse> result, Consumer<UnirestException> failure) {
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
	 * @return			The cleverbot response.
	 */

	public void say(String input, String cs, Consumer<CleverResponse> result, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("wrapper", "Elypiai");
		queryParams.put("input", input);

		if (cs != null)
			queryParams.put("cs", cs);

		Unirest.get(GET_REPLY_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				CleverResponse cleverResponse = new CleverResponse(object);

				result.accept(cleverResponse);

				if (cs != null)
					cache.remove(cs);

				cache.put(cleverResponse.getCS(), cleverResponse);
			}

			@Override
			public void failed(UnirestException e) {
				e.printStackTrace();
			}

			@Override
			public void cancelled() {

			}
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
