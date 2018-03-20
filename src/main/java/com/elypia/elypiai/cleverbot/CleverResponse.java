package com.elypia.elypiai.cleverbot;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class CleverResponse {

	private Cleverbot cleverbot;

	private String cs;
	private int interactionCount;
	private String input;
	private String output;
	private String conversationId;
	private String errorLine;
	private int timeTaken;
	private long timeElapsed;
	private String callback;
	private Map<String, String> interactions;

	private ScriptResponse script;

	public CleverResponse(Cleverbot cleverbot, JSONObject object) {
		cs 					= object.getString("cs");
		interactionCount	= object.optInt("interaction_count");
		input 				= object.getString("input");
		output				= object.getString("output");
		conversationId		= object.getString("conversation_id");
		errorLine			= object.getString("errorline");
		timeTaken			= object.optInt("time_taken");
		timeElapsed			= object.optLong("time_elapsed");
		callback			= object.optString("callback", null);

		// Start collecting passed interactions
		interactions = new TreeMap<>();

		for (int i = 50; i > 0; i--) {
			String interaction = "interaction_" + i;
			String otherInteraction = interaction + "_other";

			if (object.has(otherInteraction))
				interactions.put(object.getString(interaction), object.getString(otherInteraction));
		}

		// Other variables are unused by Cleverbot but a part of Cleverscript
		script = new ScriptResponse(this, object);
	}

	/**
	 * @return 	The state of the conversation so far, this
	 * 			is used to track your conversation. On the next
	 * 			request this should be sent back in order to keep
	 * 			the flow of the conversation going.
	 */

	public String getCS() {
		return cs;
	}

	/**
	 * @return	How many pairs of interactions this conversation has had
	 * 			so far, one interaction is user to bot, then bot to user.
	 */

	public int getInteractionCount() {
		return interactionCount;
	}

	/**
	 * @return	What the user said this interaction, will return an empty
	 * 			String if user said nothing.
	 */

	public String getInput() {
		return input;
	}

	/**
	 * @return	What the bot responded this interaction.
	 */

	public String getOutput() {
		return output;
	}

	/**
	 * @return	The conversation ID for this conversation.
	 */

	public String getConversationId() {
		return conversationId;
	}

	/**
	 * @return	Any error information from Cleverbot, this
	 * 			is not the same as error codes.
	 */

	public String getErrorLine() {
		return errorLine;
	}

	/**
	 * @return	Time taken to process the response in MS.
	 * 			This is the time taken from when they received
	 * 			the request to when they processed it.
	 */

	public int getTimeTaken() {
		return timeTaken;
	}

	/**
	 * @return	The time in seconds it has been since the first
	 * 			interaction of this conversation.
	 */

	public long getLifetime() {
		return timeElapsed;
	}

	/**
	 * @return	Get up to the past 50 interactions (input and output)
	 * 			if available, including this current interaction.
	 */

	public Map<String, String> getHistory() {
		return interactions;
	}

	public String getHistoryScript() {
		StringBuilder builder = new StringBuilder();
		Iterator<Map.Entry<String, String>> it = interactions.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();

			builder.append("User: " + entry.getKey());
			builder.append("\n");
			builder.append("Cleverbot: " + entry.getValue());

			if (it.hasNext())
				builder.append("\n");
		}

		return builder.substring(0, builder.length());
	}

	/**
	 * @return	Return values not relevent to the Cleverbot
	 * 			API but a part of CleverScript.
	 */

	public ScriptResponse getScriptResponse() {
		return script;
	}
}
