package com.elypia.elypiai.bing;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SpellCheck {

	private static final String SPELLCHECK_ENDPOINT = "https://api.cognitive.microsoft.com/bing/v5.0/spellcheck/";

	private String API_KEY;

	/**
	 * SpellCheck object for making calls for Bing SpellCheck API,
	 * this corrects spelling for Strings passed.
	 * More infromation can be found here: <a href="https://www.microsoft.com/cognitive-servi
	 * ces/en-us/bing-spell-check-api/documentation">API Overview</a> <br>
	 *
	 * @param	api_key		API key obtained from Bing.
	 * @return				SpellCheck object.
	 * @see <a href="https://www.microsoft.com/cognitive-services/en-us/apis">Bing Cognitive Services</a>
	 */

	public SpellCheck(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * Proof reads the provided String and makes all suggested
	 * corrections before returning.
	 *
	 * @param	body	The body of text to proof read.
	 * @return			The body with all suggested corrections.
	 */

	public void proofRead(String body, Consumer<String> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("mode", "proof");
		queryParams.put("text", body);

		Map<String, String> headers = new HashMap<>();
		headers.put("Ocp-Apim-Subscription-Key", API_KEY);

		Unirest.get(SPELLCHECK_ENDPOINT).queryString(queryParams).headers(headers).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();
				JSONArray result = object.getJSONArray("flaggedTokens");
				StringBuilder builder = new StringBuilder(body);

				for(int count = result.length() - 1; count >= 0; count--) {
					object = result.getJSONObject(count);
					int offset = object.getInt("offset");
					builder.replace(offset, offset + object.getString("token").length(), object.getJSONArray("suggestions").getJSONObject(0).getString("suggestion"));
				}

				success.accept(builder.toString());
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