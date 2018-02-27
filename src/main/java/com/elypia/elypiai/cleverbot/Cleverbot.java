package com.elypia.elypiai.cleverbot;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Cleverbot {

	public static final String GET_REPLY_ENDPOINT = "https://www.cleverbot.com/getreply";

	private final String API_KEY;

	/**
	 * Creates the Cleverbot object in order to make requests
	 * to the cleverbot API, apikey can be obtained from
	 * cleverbot website for free.
	 *
	 * @param	apikey 	API key recieved upon signing up.
	 * @return			Cleverbot
	 * @see <a href="https://www.cleverbot.com/api/">cleverbot</a>
	 */

	public Cleverbot(String apiKey) {
		API_KEY = apiKey;
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

	public void sayCS(String cs, String input, Consumer<CleverResponse> result) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("wrapper", "Utopiai");
		queryParams.put("input", input);

		if (cs != null)
			queryParams.put("cs", cs);

		Unirest.get(GET_REPLY_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				CleverResponse cleverResponse = new CleverResponse(response.getBody().getObject());
				result.accept(cleverResponse);
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
}