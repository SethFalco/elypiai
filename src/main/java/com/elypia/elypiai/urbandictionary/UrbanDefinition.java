package com.elypia.elypiai.urbandictionary;

import com.google.gson.annotations.SerializedName;

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
	private String currentVote;

	@SerializedName("example")
	private String example;

	@SerializedName("thumbs_down")
	private int thumbsDown;

	/**
	 * @return 	The definition of the word.
	 */

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	/**
	 * @return	A permanent URL that will always link to this defintion.
	 */

	public String getPermaLink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	/**
	 * @return	The total number of thumbs up the definition has.
	 */

	public int getThumbsUp() {
		return thumbsUp;
	}

	public void setThumbsUp(int thumbsUp) {
		this.thumbsUp = thumbsUp;
	}

	/**
	 * @return	The name of the author of this definition.
	 */

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return	The word that was defined.
	 */

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return	The unique id of this definition.
	 */

	public int getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(int definitionId) {
		this.definitionId = definitionId;
	}

	/**
	 * @return	I have no idea. ^-^'
	 */

	public Object getCurrentVote() {
		return currentVote;
	}

	public void setCurrentVote(String currentVote) {
		this.currentVote = currentVote;
	}

	/**
	 * @return	A demonstration of how the word is used.
	 */

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	/**
	 * @return	The total number of down votes the definition has gotten.
	 */

	public int getThumbsDown() {
		return thumbsDown;
	}

	public void setThumbsDown(int thumbsDown) {
		this.thumbsDown = thumbsDown;
	}
}
