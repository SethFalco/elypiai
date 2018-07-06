package com.elypia.elypiai.nanowrimo;


import javax.xml.bind.annotation.*;

@XmlRootElement(namespace = "", name = "wc")
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

	@XmlElement(name = "error")
	private String error;

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

	public String getError() {
		return error;
	}
}
