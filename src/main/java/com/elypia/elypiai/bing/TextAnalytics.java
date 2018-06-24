package com.elypia.elypiai.bing;

import com.elypia.elypiai.utils.okhttp.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TextAnalytics {

	public static final String POST_SENTIMENT = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/sentiment";
	public static final String POST_KEYPHRASES = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/keyPhrases";
	public static final String POST_DETECTLANGUAGES = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/languages";
	public static final String POST_DETECTTOPICS = "https://westus.api.cognitive.microsoft.com/text/analytics/v2.0/topics";

	private final String API_KEY;

	/**
	 * Bing Text Analytics allows users to find the language of text,
	 * pull significant information such as topics and key phrases
	 * or find the sentiment behind a message as a numeric value.
	 *
	 * More infromation can be found here:
	 * <a href="https://www.microsoft.com/cognitive-services/en-us/text-analytics/documentation">API Overview</a>
	 *
	 * @param	apiKey		API key obtained from Bing.
	 * @see <a href="https://www.microsoft.com/cognitive-services/en-us/apis">Bing Cognitive Services</a>
	 */

	public TextAnalytics(String apiKey) {
		API_KEY = apiKey;
	}

	/**
	 * Returns the language detected for all the Strings in the array
	 * in the form of a Map with the key as the String provided
	 * and value as the Languages detected.
	 *
	 * @param	array	Strings to detect language from.
	 * @param success What to perform with the result.
	 * @param failure What to perform in case of failure, eg timeout.
	 */

	public void detectLanguages(String[] array, Consumer<Map<String, String>> success, Consumer<IOException> failure) {
		JSONArray documents = new JSONArray();

		for (int i = 0; i < array.length; i++)
			documents.put(new JSONObject().put("text", array[i]).put("id", i));

		JSONObject formdata = new JSONObject().put("documents", documents);

		Request req = new Request(POST_DETECTLANGUAGES);
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);
		req.setFormData(formdata);

		req.post(result -> {
			JSONObject object = result.asJSONObject();
			JSONArray docs = object.getJSONArray("documents");
			Map<String, String> map = new HashMap<>();

			for (int i = 0; i < documents.length(); i++) {
				JSONObject o = docs.getJSONObject(i).getJSONArray("detectedLanguages").getJSONObject(0);
				String name = o.getString("name");
				map.put(array[i], name);
			}

			success.accept(map);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Returns the language detected from the String provided.
	 *
	 * @param body String to detect language from.
	 * @param success What to perform with the result.
	 * @param failure What to perform in case of failure, eg timeout.
	 */

	public void detectLanguage(String body, Consumer<String> success, Consumer<IOException> failure) {
		String[] array = new String[] {body};

		detectLanguages(array, result -> {
			String language = result.get(body);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Requires a minimum of 100 Documents (Strings) for topic
	 * detection. This will NOT return the topics found. This method
	 * will post the request to Bing to begin processing of the data
	 * provided in order to return the topics later.
	 * Expected to do: {@link #detectTopicsStatus(String, Consumer, Consumer)} periodically
	 * using the recieved operation ID in order to check if the
	 * documents have finished processing yet, then {@link #getDetectedTopics(String, Consumer, Consumer)}
	 * to collect the result. <br>
	 * Do note: <br>
	 * The documents can take a long time to process. <br>
	 * The rate limit for this is 5 submissions every 5 minutes.
	 *
	 * @param	documents			Array of documents to search detect topics.
	 * @param	topicsToExclude		Topics to ignore if found.
	 * @param	stopWords			Words ignored entirely from the topics (Includes similar and pluruls.).
	 */

	public void detectTopics(String[] documents, String[] topicsToExclude, String[] stopWords, Consumer<String> success, Consumer<IOException> failure)  {
		if (documents.length < 100)
			throw new IllegalArgumentException("This request requires a minimum of 100 documents.");

		JSONArray stopWordsJson = new JSONArray();
		for (String s : stopWords)
			stopWordsJson.put(s);

		JSONArray topicsToExcludeJson = new JSONArray();
		for (String s : topicsToExclude)
			topicsToExcludeJson.put(s);

		JSONArray documentsJson = new JSONArray();
		for (int i = 0; i < documents.length; i++)
			documentsJson.put(new JSONObject().put("text", documents[i]).put("id", i));

		JSONObject formdata = new JSONObject();
		formdata.put("documents", documentsJson);
		formdata.put("stopWords", stopWordsJson);
		formdata.put("topicsToExclude", topicsToExcludeJson);

		Request req = new Request(POST_DETECTTOPICS);
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);
		req.setFormData(formdata);

		req.post(result -> {
			String location = result.getHeader("Location");
			success.accept(location);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Expected after {@link #detectTopics(String[], String[], String[], Consumer, Consumer)}
	 * Will retrn if the operation provided has completed.
	 * If true follow with {@link #getDetectedTopics(String, Consumer, Consumer)} to return topics.
	 *
	 * @param	operation	The operationId provided from
	 * 						{@link #detectTopics(String[], String[], String[], Consumer, Consumer)}.
	 */

	public void detectTopicsStatus (String operation, Consumer<Boolean> success, Consumer<IOException> failure) {
		Request req = new Request(operation);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			String status = object.getString("status");
			boolean succesful = status.equals("Succeeded");

			success.accept(succesful);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Expected after {@link #detectTopicsStatus(String, Consumer, Consumer)}}
	 * Will return the {@link Collection} of topics detected from
	 * the documents provided. <br>
	 * Possible null: if operation is incomplete or invalid.
	 *
	 * @param	operation	The operationId provided from
	 * 						{@link #detectTopics(String[], String[], String[], Consumer, Consumer)}}.
	 */

	public void getDetectedTopics(String operation, Consumer<Collection<String>> success, Consumer<IOException> failure) {
		Request req = new Request(operation);

		req.get(result -> {
			JSONObject object = result.asJSONObject();
			JSONArray topics = object.getJSONObject("operationProcessingResult").getJSONArray("topics");

			Collection<String> keyPhrases = new ArrayList<>();
			for (int i = 0; i < topics.length(); i++)
				keyPhrases.add(topics.getJSONObject(i).getString("keyPhrase"));

			success.accept(keyPhrases);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Takes the String provided and returns all keyphrases
	 * as a {@link Collection}.
	 *
	 * @param	body	String to check keyphrases for.
	 */

	public void keyPhrases(String body, Consumer<Collection<String>> success, Consumer<IOException> failure) {
		JSONObject object = new JSONObject();
		object.put("text", body);
		object.put("id", body);

		JSONArray documents = new JSONArray();
		documents.put(object);

		JSONObject formdata = new JSONObject();
		formdata.put("documents", documents);

		Request req = new Request(POST_KEYPHRASES);
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);
		req.setFormData(formdata);

		req.post(result -> {
			JSONArray array = result.asJSONObject().getJSONArray("documents").getJSONObject(0).getJSONArray("keyPhrases");

			Collection<String> keyPhrases = new ArrayList<>();
			for (int i = 0; i < array.length(); i++)
				keyPhrases.add(array.getString(i));

			success.accept(keyPhrases);
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Takes the string provided and finds the sentiment
	 * in the form of a numerican value between 0 and 1;
	 * a value representing better.
	 *
	 * @param body String to check.
	 * @param success What to do with the result.
	 * @param failure What to do in case of failure, eg timeout.
	 */

	public void sentiment(String body, Consumer<Double> success, Consumer<IOException> failure) {
		JSONArray documents = new JSONArray();

		JSONObject object = new JSONObject();
		object.put("text", body);
		object.put("id", body);

		documents.put(object);

		JSONObject formdata = new JSONObject();
		formdata.put("documents", documents);

		Request req = new Request(POST_SENTIMENT);
		req.addHeader("Ocp-Apim-Subscription-Key", API_KEY);
		req.setFormData(formdata);

		req.post(result -> {
			JSONObject obj = result.asJSONObject();
			success.accept(obj.getJSONArray("documents").getJSONObject(0).getDouble("score"));
		}, err -> {
			failure.accept(err);
		});
	}
}
