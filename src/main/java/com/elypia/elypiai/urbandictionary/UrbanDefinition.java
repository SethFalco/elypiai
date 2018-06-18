package com.elypia.elypiai.urbandictionary;

import com.google.gson.JsonObject;

public class UrbanDefinition {

	private String definition;
	private String permalink;
	private int thumbsUp;
	private String author;
	private String word;
	private int definitionId;
	private Object currentVote;
	private String example;
	private int thumbsDown;

	UrbanDefinition(JsonObject object) {
		definition = object.get("definition").getAsString();
		permalink = object.get("permalink").getAsString();
		thumbsUp = object.get("thumbs_up").getAsInt();
		author = object.get("author").getAsString();
		word = object.get("word").getAsString();
		definitionId = object.get("defid").getAsInt();
		example = object.get("example").getAsString();
		thumbsDown = object.get("thumbs_down").getAsInt();

		currentVote = object.get("current_vote");

		if (currentVote.equals(""))
			currentVote = null;
	}

	/**
	 * @return 	The definition of the word.
	 */

	public String getDefinition() {
		return definition;
	}

	/**
	 * @return	A permanent URL that will always link to this defintion.
	 */

	public String getPermaLink() {
		return permalink;
	}

	/**
	 * @return	The total number of thumbs up the definition has.
	 */

	public int getThumbsUp() {
		return thumbsUp;
	}

	/**
	 * @return	The name of the author of this definition.
	 */

	public String getAuthor() {
		return author;
	}

	/**
	 * @return	The word that was defined.
	 */

	public String getWord() {
		return word;
	}

	/**
	 * @return	The unique id of this definition.
	 */

	public int getDefinitionId() {
		return definitionId;
	}

	/**
	 * @return	I have no idea. ^-^'
	 */

	public Object getCurrentVote() {
		return currentVote;
	}

	/**
	 * @return	A demonstration of how the word is used.
	 */

	public String getExample() {
		return example;
	}

	/**
	 * @return	The total number of down votes the definition has gotten.
	 */

	public int getThumbsDown() {
		return thumbsDown;
	}
}
