package com.elypia.elypiai.urbandictionary;

import com.elypia.elypiai.utils.gson.deserializers.StringEmptyIsNullDeserializer;
import com.google.gson.annotations.*;

public class UrbanDefinition {

	@SerializedName("definition")
	private String definition;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("thumbs_up")
	private int thumbsUp;

	@SerializedName("author")
	private String author;

	@SerializedName("word")
	private String word;

	@SerializedName("defid")
	private int definitionId;

	@SerializedName("current_vote")
	@JsonAdapter(StringEmptyIsNullDeserializer.class)
	private String currentVote;

	@SerializedName("example")
	private String example;

	@SerializedName("thumbs_down")
	private int thumbsDown;

	private UrbanDefinition() {

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
