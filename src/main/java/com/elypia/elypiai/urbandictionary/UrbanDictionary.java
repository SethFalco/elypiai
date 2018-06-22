package com.elypia.elypiai.urbandictionary;


import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class UrbanDictionary {

	public static final String DEFINE = "http://api.urbandictionary.com/v0/define";

	/**
	 * Returns a defintion from Urban Dictionary if result(s)
	 * are found matching exactly the word or phrase provided. <br>
	 * Possible null: Returns null if no definitions are found.
	 *
	 * @param term The word or phrase to be defined.
	 * @param success What to do with the result of this call.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void define(String term, Consumer<UrbanResult> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(DEFINE);
		req.addParam("term", term);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			UrbanResult urbanResult = new UrbanResult(object, term);

			success.accept(urbanResult);
		}, failure);
	}
}
