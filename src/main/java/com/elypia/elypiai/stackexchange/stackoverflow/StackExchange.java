package com.elypia.elypiai.stackexchange.stackoverflow;

import com.elypia.elypiai.utils.okhttp.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class StackExchange {

	public static final String SIMILAR_ENDPOINT = "https://api.stackexchange.com/2.2/similar";

	private String apiKey;

	public StackExchange(String apiKey) {
        this.apiKey = apiKey;
	}

	public void ask(Consumer<String> success, Consumer<IOException> failure, String question) {
	    ask(question, null, success, failure);
    }

	/**
	 * Asks a question on StackOverflow with the given question
	 * and tags and returns only the url as a String of
	 * the most relevant result with an accepted answer.
	 *
	 * @param question The question to search for.
	 * @param tags The tags associated with the question.
	 * @param success What to do when succesful.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void ask(String question, String[] tags, Consumer<String> success, Consumer<IOException> failure) {
		Request req = new Request(SIMILAR_ENDPOINT);
	    req.addParam("key", apiKey);
		req.addParam("order", "desc");
		req.addParam("sort", "relevance");
		req.addParam("site", "stackoverflow");
		req.addParam("accepted", true);
		req.addParam("title", question);

	    if (tags != null) {
			if (tags.length > 0)
				req.addParam("C", String.join(";", tags));
		}

		req.get(result -> {
			JSONObject object = result.asJSONObject();

			JSONArray items = object.getJSONArray("items");
			JSONObject item = items.getJSONObject(0);

			success.accept(item.getString("link"));
		}, err -> {
			failure.accept(err);
		});
	}
}
