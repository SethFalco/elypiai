package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.NanoError;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

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
	private NanoError error;

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

	protected NanoError getError() {
		return error;
	}
}
