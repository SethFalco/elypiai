/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.cleverbot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class CleverResponse {

	/** @see #getCs() */
	@SerializedName("cs")
	private String cs;

	/** @see #getInteractionCount() */
	@SerializedName("interaction_count")
	private int interactionCount;

	/** @see #getInput() */
	@SerializedName("input")
	private String input;

	/** @see #getOutput() */
	@SerializedName("output")
	private String output;

	/** @see #getConversationId() */
	@SerializedName("conversation_id")
	private String conversationId;

	/** @see #getErrorLine() */
	@SerializedName("errorline")
	private String errorLine;

	/** @see #getTimeTaken() */
	@SerializedName("time_taken")
	private int timeTaken;

	/** @see #getTimeElapsed() */
	@SerializedName("time_elapsed")
	private long timeElapsed;

	@SerializedName("callback")
	private String callback;

	/** @see #getInteractions() */
	@SerializedName("interactions")
	private List<Interaction> interactions;

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
	public List<Interaction> getInteractions() {
		return interactions;
	}
}
