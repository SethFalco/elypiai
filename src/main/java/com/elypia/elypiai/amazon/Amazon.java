package com.elypia.elypiai.amazon;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Amazon {

	private String accessKey;
	private String id;
	private AmazonEndpoint endpoint;

	private RequestSigner signer;

	public Amazon(String accessKey, String secret, String id) {
		this(accessKey, secret, id, AmazonEndpoint.US);
	}

	public Amazon(String accessKey, String secret, String id, AmazonEndpoint endpoint) {
		this.accessKey = accessKey;
		this.id = id;
		this.endpoint = endpoint;

		signer = new RequestSigner(secret);
	}

	/**
	 * Searches Amazon with the selected endpoint
	 * {@link #setEndpoint} and grabs the
	 * url of the most relevant result.
	 *
	 * @param	product 	Product to search for.
	 * @return				URL of top result product.
	 */

	public void fetchProduct(String product, Consumer<String> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new LinkedHashMap<>();
		queryParams.put("AWSAccessKeyId", accessKey);
		queryParams.put("AssociateTag", id);
		queryParams.put("Keywords", product);
		queryParams.put("Operation", "ItemSearch");
		queryParams.put("SearchIndex", "All");
		queryParams.put("Service", "AWSECommerceService");
		queryParams.put("Timestamp", Instant.now());
		queryParams.put("Version", "2013-08-01");

		String url = signer.sign(endpoint, queryParams);

		Unirest.get(url).asStringAsync(new Callback<String>() {

			@Override
			public void completed(HttpResponse<String> response) {
				Document document = Jsoup.parse(response.getBody(), url, Parser.xmlParser());
				Elements elements = document.getElementsByTag("Message");
				String string = null;

				if (elements.size() > 0) {
					if (elements.first().text().equals("We did not find any matches for your request."))
						string = null;
				} else {
					string = String.format("%s/dp/%s?tag%s", endpoint.getShoppingUrl(), document.getElementsByTag("ASIN").get(0).text(), id);
				}

				success.accept(string);
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

	/**
	 * Changed the selected endpoint to do requests for
	 * Amazon.
	 *
	 * @param	endpoint	Endpoint to use for Amazon requests.
	 */

	public void setEndpoint(AmazonEndpoint endpoint) {
		this.endpoint = endpoint;
	}
}