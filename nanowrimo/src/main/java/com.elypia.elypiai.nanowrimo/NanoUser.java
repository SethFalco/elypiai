package com.elypia.elypiai.nanowrimo;


import com.elypia.elypiai.nanowrimo.data.UserWordCountError;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement
public class NanoUser {

	private static final String PROFILE_URL = "https://nanowrimo.org/participants/%s";

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
	private UserWordCountError error;

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
		return String.format(PROFILE_URL, username.replace(" ", "-"));
	}

	public List<WordCountEntry> getEntries() {
		if (entries == null)
			entries = new ArrayList<>();

		return entries;
	}

	public UserWordCountError getError() {
		return error;
	}
}
