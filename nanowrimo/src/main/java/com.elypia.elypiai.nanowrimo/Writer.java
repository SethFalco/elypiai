package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.WordCountError;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "wchistory")
public class Writer {

	private static final String PROFILE_URL = "https://nanowrimo.org/participants/";

	@XmlElement(name = "uname")
	private String username;

	@XmlElement(name = "user_wordcount")
	private int wordCount;

	@XmlElement(name = "uid")
	private int id;

	@XmlElement(name = "winner")
	private boolean winner;

	@XmlElementWrapper(name = "wordcounts")
	@XmlElement(name = "wcentry")
	private List<WordCountEntry> entries;

	@XmlElement(name = "error")
	private WordCountError error;

	public String getUsername() {
		return username;
	}

	public int getWordCount() {
		return wordCount;
	}

	public int getId() {
		return id;
	}

	public boolean isWinner() {
		return winner;
	}

	public String getUrl() {
		return PROFILE_URL + username.replace(" ", "-");
	}

	public List<WordCountEntry> getEntries() {
		if (entries == null)
			entries = new ArrayList<>();

		return entries;
	}

	public WordCountError getError() {
		return error;
	}
}
