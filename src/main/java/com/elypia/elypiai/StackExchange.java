package com.elypia.elypiai;

import com.sethsutopia.utopiai.restful.*;
import com.sethsutopia.utopiai.restful.RestBuilder.RestMethod;

public class StackExchange {

	public static final String SIMILAR_ENDPOINT = "https://api.stackexchange.com/2.2/similar";

	private final RestBuilder GET_SIMILAR;

	public StackExchange(String api_key) {
		GET_SIMILAR = new RestBuilder(RestMethod.GET, SIMILAR_ENDPOINT);

		GET_SIMILAR.addParam("key", api_key);
		GET_SIMILAR.addParam("order", "desc");
		GET_SIMILAR.addParam("sort", "relevance");
		GET_SIMILAR.addParam("site", "stackoverflow");
		GET_SIMILAR.addParam("accepted", "True");
	}

	/**
	 * Asks a question on StackOverflow with the given question
	 * and tags and returns only the url as a String of
	 * the most relevant result with an accepted answer.
	 *
	 * @throws RestfulException
	 * 	 *
	 * @param	question 	The question to search for.
	 * @param	tags 		The tags associated with the question.
	 * @return				Url of the most relevant result with accepted answer.
	 */

	public String ask(String question, String[] tags) throws RestException {
		return ask(question, String.join(";", tags));
	}

	/**
	 * Asks a question on StackOverflow with the given question
	 * and tags and returns only the url as a String of
	 * the most relevant result with an accepted answer.
	 *
	 * @throws RestfulException
	 * 	 *
	 * @param	question 	The question to search for.
	 * @param	tags 		The tags associated with the question, seperated by semi-colons ";".
	 * @return				Url of the most relevant result with accepted answer.
	 */

	public String ask(String question, String tags) throws RestException {
		GET_SIMILAR.addParam("C", tags);
		GET_SIMILAR.addParam("title", question);
		return GET_SIMILAR.executeRequest().getJSONObject().getJSONArray("items").getJSONObject(0).getString("link");
	}

}