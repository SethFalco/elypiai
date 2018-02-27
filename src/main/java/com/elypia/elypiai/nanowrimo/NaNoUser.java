package com.elypia.elypiai.nanowrimo;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NaNoUser {

	private static String PROFILE_URL = "https://nanowrimo.org/participants/%s";

	private String username;
	private int wordCount;
	private int id;
	private boolean winner;
	private String profileUrl;
	private String error;

	public NaNoUser(Document document) {
		Elements elements = document.getElementsByTag("error");

		if (elements.size() > 0) {
			error = elements.first().text();
		} else {
			username = document.getElementsByTag("uname").text();
			profileUrl = String.format(PROFILE_URL, username.replace(" ", "-"));
			wordCount = Integer.parseInt(document.getElementsByTag("user_wordcount").first().text());
			id = Integer.parseInt(document.getElementsByTag("uid").first().text());
			winner = Boolean.parseBoolean(document.getElementsByTag("winner").first().text());
		}
	}

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

	public String getProfileUrl() {
		return profileUrl;
	}

	public String getError() {
		return error;
	}
}
