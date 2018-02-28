package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.WordCountError;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Nanowrimo {

	public static final String PUT_WORDCOUNT_ENDPOINT = "https://nanowrimo.org/api/wordcount";
	public static final String GET_USER_ENDPOINT = "https://nanowrimo.org/wordcount_api/wc/{username}";

	public void updateWordCount(String secret, String name, int wordCount, Consumer<WordCountError> success, Consumer<UnirestException> failure) {
		secret = String.format("%s%s%s", secret, name, wordCount);
		secret = DigestUtils.sha1Hex(secret);
		name = name.replace(" ", "%20");

		String body = String.format("hash=%s&name=%s&wordcount=%d", secret, name, wordCount);

		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "application/x-www-form-urlencoded");

		Unirest.put(PUT_WORDCOUNT_ENDPOINT).headers(headers).body(body).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				String resp = response.getBody().toUpperCase().replace(" ", "_");
				success.accept(WordCountError.valueOf(resp));
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

	public void getNanoUser(String username, Consumer<NanoUser> success, Consumer<UnirestException> failure) {
		username = username.replace(" ", "-");

		HttpRequestWithBody request = Unirest.put(GET_USER_ENDPOINT).routeParam("username", username);
		String url = request.getUrl();

		request.asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				Document document = Jsoup.parse(response.getBody(), url, Parser.xmlParser());
				Elements elements = document.getElementsByTag("error");
				boolean exists = true;

				for (Element element : elements) {
					if (element.text().equals("user does not exist"))
						exists = false;
				}

				NanoUser user = exists ? new NanoUser(document) : null;
				success.accept(user);
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
