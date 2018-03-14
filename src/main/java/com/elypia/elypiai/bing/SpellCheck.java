package com.elypia.elypiai.bing;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class SpellCheck {

	private static final String SPELLCHECK_ENDPOINT = "https://api.cognitive.microsoft.com/bing/v5.0/spellcheck/";

	private String API_KEY;

	/**
	 * SpellCheck object for making calls for Bing SpellCheck API,
	 * this corrects spelling for Strings passed.
	 * More infromation can be found here:
	 * <a href="https://www.microsoft.com/cognitive-services/en-us/bing-spell-check-api/documentation">API Overview</a>
	 *
	 * @param	apiKey		API key obtained from Bing.
	 * @see <a href="https://www.microsoft.com/cognitive-services/en-us/apis">Bing Cognitive Services</a>
	 */

	public SpellCheck(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * Proof reads the provided String and makes all suggested
	 * corrections before returning.
	 *
	 * @param body The body of text to proof read.
	 * @param success What to perform with the result.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void proofRead(String body, Consumer<String> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(SPELLCHECK_ENDPOINT);
		req.addParam("mode", "proof");
		req.addParam("text", body);
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			JSONArray array = object.getJSONArray("flaggedTokens");
			StringBuilder builder = new StringBuilder(body);

			for (int i = array.length() - 1; i >= 0; i--) {
				JSONObject typo = array.getJSONObject(i);
				int offset = typo.getInt("offset");
				String wrong = typo.getString("token");
				String corrected = typo.getJSONArray("suggestions").getJSONObject(0).getString("suggestion");
				builder.replace(offset, offset + wrong.length(), corrected);
			}

			success.accept(builder.toString());
		}, err -> {
			failure.accept(err);
		});
	}
}
