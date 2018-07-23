package com.elypia.elypiai.cleverbot;

import com.elypia.elypiai.utils.Tuple;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class CleverResponse {

	@SerializedName("cs")
	private String cs;

	@SerializedName("interaction_count")
	private int interactionCount;

	@SerializedName("input")
	private String input;

	@SerializedName("output")
	private String output;

	@SerializedName("conversation_id")
	private String conversationId;

	@SerializedName("errorline")
	private String errorLine;

	@SerializedName("time_taken")
	private int timeTaken;

	@SerializedName("time_elapsed")
	private long timeElapsed;

	@SerializedName("callback")
	private String callback;

	private List<Tuple<String, String>> interactions;

	/**
	 * @return 	The state of the conversation so far, this
	 * 			is used to track your conversation. On the next
	 * 			request this should be sent back in order to keep
	 * 			the flow of the conversation going.
	 */

	public String getCs() {
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

	public long getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * @return	Get up to the past 50 interactions (input and output)
	 * 			if available, including this current interaction.
	 */

	public List<Tuple<String, String>> getHistory() {
		return interactions;
	}

	public String getHistoryScript() {
		StringJoiner joiner = new StringJoiner("\n");

		interactions.forEach((tuple) -> {
			joiner.add("User: " + tuple.itemOne());
			joiner.add("Cleverbot: " + tuple.itemTwo());
		});

		return joiner.toString();
	}

	public void setHistory(List<Tuple<String, String>> interactions) {
		this.interactions = interactions;
	}
}
