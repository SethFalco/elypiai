package com.elypia.elypiai.myanimelist;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.codec.Charsets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.util.Base64;
import java.util.function.Consumer;

public class MyAnimeList {

	public static final String GET_SEARCH_ANIME = "https://myanimelist.net/api/anime/search.xml";
	public static final String GET_SEARCH_MANGA = "https://myanimelist.net/api/manga/search.xml";

	private final String AUTH;

	/**
	 * Allows access to MyAnimeList API.
	 *
	 * @param	username	MyAnimeList account username.
	 * @param	password	MyAnimeList account password.
	 */

	public MyAnimeList(String username, String password) {
		byte[] authString = (username + ":" + password).getBytes(Charsets.UTF_8);
		AUTH = "Basic " + Base64.getEncoder().encodeToString(authString);
	}

	/**
	 * Allows access to MyAnimeList API.
	 *
	 * @param	auth	To prevent storing password in code or file, can provide
	 * 					pre encoded auth string for authorisation in the format {username}:{password}.
	 * 					(Do note it's just as easy to decode as it is to encode a Base64 String.)
	 */

	public MyAnimeList(String auth) {
		if (auth.startsWith("Basic "))
			AUTH = auth;
		else
			AUTH = "Basic " + auth;
	}

	public void getAnime(String q, Consumer<Anime> success, Consumer<UnirestException> failure) {
		Unirest.get(GET_SEARCH_ANIME).queryString("q", q).header("Authorization", AUTH).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				if (response.getBody() == null) {
					success.accept(null);
					return;
				}

				Document doc = Jsoup.parse(response.getBody(), "", Parser.xmlParser());
				success.accept(new Anime(doc));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	public void getManga(String q, Consumer<Manga> success, Consumer<UnirestException> failure) {
		Unirest.get(GET_SEARCH_MANGA).queryString("q", q).header("Authorization", AUTH).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {

				if (response.getBody() == null) {
					success.accept(null);
					return;
				}

				Document doc = Jsoup.parse(response.getBody(), "", Parser.xmlParser());
				success.accept(new Manga(doc));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}
}
