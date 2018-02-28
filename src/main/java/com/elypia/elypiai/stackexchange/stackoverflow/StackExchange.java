package com.elypia.elypiai.stackexchange.stackoverflow;

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

public class StackExchange {

	public static final String SIMILAR_ENDPOINT = "https://api.stackexchange.com/2.2/similar";

	private String apiKey;

	public StackExchange(String apiKey) {
        this.apiKey = apiKey;
	}

	public void ask(Consumer<String> success, Consumer<UnirestException> failure, String question) {
	    ask(success, failure, question, new String[0]);
    }

	/**
	 * Asks a question on StackOverflow with the given question
	 * and tags and returns only the url as a String of
	 * the most relevant result with an accepted answer.
	 *
	 * @param	question 	The question to search for.
	 * @param	tags 		The tags associated with the question.
	 * @return				Url of the most relevant result with accepted answer.
	 */

	public void ask(Consumer<String> success, Consumer<UnirestException> failure, String question, String... tags) {
	    Map<String, Object> params = new HashMap<>();
	    params.put("key", apiKey);
	    params.put("order", "desc");
	    params.put("sort", "relevance");
	    params.put("site", "stackoverflow");
	    params.put("accepted", true);
	    params.put("title", question);

	    if (tags.length > 0)
	        params.put("C", String.join(";", tags));

        Unirest.get(SIMILAR_ENDPOINT).queryString(params).asJsonAsync(new Callback<JsonNode>() {
            @Override
            public void completed(HttpResponse<JsonNode> response) {
                JSONObject object = response.getBody().getObject();

                JSONArray items = object.getJSONArray("items");
                JSONObject item = items.getJSONObject(0);

                success.accept(item.getString("link"));
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
