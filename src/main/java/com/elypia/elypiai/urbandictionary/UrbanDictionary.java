package com.elypia.elypiai.urbandictionary;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.function.Consumer;

public class UrbanDictionary {

	public static final String GET_DEFINE_ENDPOINT = "http://api.urbandictionary.com/v0/define";

	/**
	 * Returns a defintion from Urban Dictionary if result(s)
	 * are found matching exactly the word or phrase provided. <br>
	 * Possible null: Returns null if no definitions are found.
	 *
	 * @param	term 	The word or phrase to be defined.
	 * @return			All definitions and other information on the word.
	 */

	public void define(String term, Consumer<UrbanResult> success, Consumer<UnirestException> failure) {
		Unirest.get(GET_DEFINE_ENDPOINT).queryString("term", term).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				success.accept(new UrbanResult(response.getBody().getObject(), term));
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
