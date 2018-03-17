package com.elypia.elypiai.urbandictionary;

import org.json.JSONObject;

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

	UrbanDefinition(JSONObject object) {
		definition = object.getString("definition");
		permalink = object.getString("permalink");
		thumbsUp = object.getInt("thumbs_up");
		author = object.getString("author");
		word = object.getString("word");
		definitionId = object.getInt("defid");
		example = object.getString("example");
		thumbsDown = object.getInt("thumbs_down");

		currentVote = object.getString("current_vote");

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
