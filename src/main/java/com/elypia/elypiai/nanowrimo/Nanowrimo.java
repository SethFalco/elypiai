package com.elypia.elypiai.nanowrimo;

import com.elypia.elypiai.nanowrimo.data.WordCountError;
import com.elypia.elypiai.utils.okhttp.Request;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.function.Consumer;

public class Nanowrimo {

	public static final String PUT_WORDCOUNT_ENDPOINT = "https://nanowrimo.org/api/wordcount";
	public static final String GET_USER_ENDPOINT = "https://nanowrimo.org/wordcount_api/wc/%s";

	public void updateWordCount(String secret, String name, int wordCount, Consumer<WordCountError> success, Consumer<IOException> failure) {
		secret = secret + name + wordCount;
		secret = DigestUtils.sha1Hex(secret);
		name = name.replace(" ", "%20");

		String body = String.format("hash=%s&name=%s&wordcount=%d", secret, name, wordCount);

		Request req = new Request(PUT_WORDCOUNT_ENDPOINT);
		req.setFormData(body);

		req.post(result -> {
			String resp = result.toString().toUpperCase().replace(" ", "_");
			success.accept(WordCountError.valueOf(resp));
		}, err ->{
			failure.accept(err);
		});
	}

	public void getNanoUser(String username, Consumer<NanoUser> success, Consumer<IOException> failure) {
		username = username.replace(" ", "-");
		Request req = new Request(GET_USER_ENDPOINT, username);

		req.get(result -> {
			Document document = result.asDocument(Parser.xmlParser());

			Elements elements = document.getElementsByTag("error");
			boolean exists = true;

			for (Element element : elements) {
				if (element.text().equals("user does not exist"))
					exists = false;
			}

			NanoUser user = exists ? new NanoUser(document) : null;
			success.accept(user);
		}, err -> {
			failure.accept(err);
		});
	}
}
