package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import com.elypia.elypiai.amazon.data.AmazonGroup;
import com.elypia.elypiai.amazon.data.AmazonIndex;
import com.elypia.elypiai.utils.Regex;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Amazon {

	private final static AmazonGroup[] DEFAULT_GROUPS = {
		AmazonGroup.IMAGES,
		AmazonGroup.ITEM_ATTRIBUTES,
		AmazonGroup.OFFERS
	};

	private String accessKey;
	private String secret;
	private String id;
	private AmazonRequester requester;
	private AmazonEndpoint endpoint;

	/**
	 * Calls {@link #Amazon(String, String, String, AmazonEndpoint)}
	 * with AmazonEndpoint set to {@link AmazonEndpoint#US} by default.
	 *
	 * @param accessKey
	 * @param secret
	 * @param id
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */

	public Amazon(String accessKey, String secret, String id) throws InvalidKeyException, NoSuchAlgorithmException {
		this(accessKey, secret, id, AmazonEndpoint.US);
	}

	/**
	 * Creates a new instance of Amazon for the id and endpoint provided.
	 *
	 * @param accessKey
	 * @param secret
	 * @param id
	 * @param endpoint
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */

	public Amazon(String accessKey, String secret, String id, AmazonEndpoint endpoint) throws InvalidKeyException, NoSuchAlgorithmException {
		this.accessKey 	= Objects.requireNonNull(accessKey);
		this.secret 	= Objects.requireNonNull(secret);
		this.id 		= Objects.requireNonNull(id);
		this.endpoint 	= Objects.requireNonNull(endpoint);

		if (!Regex.AMAZON_ACCESS_KEY.matches(accessKey))
			throw new IllegalArgumentException("Invalid Amazon Access Key provided.");

		if (!Regex.AMAZON_SECRET.matches(secret))
			throw new IllegalArgumentException("Invalid Amazon Secret provided.");

		requester = new AmazonRequester(this);
	}

	public void getItem(String product, Consumer<List<AmazonItem>> success, Consumer<UnirestException> failure) {
		getItem(product, DEFAULT_GROUPS, success, failure);
	}

	public void getItem(String product, AmazonGroup[] groups, Consumer<List<AmazonItem>> success, Consumer<UnirestException> failure) {
		getItem(product, groups, AmazonIndex.ALL, success, failure);
	}

	public void getItem(String product, AmazonGroup[] groups, AmazonIndex index, Consumer<List<AmazonItem>> success, Consumer<UnirestException> failure) {
		requester.getItem(product, groups, index, success, failure);
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecret() {
		return secret;
	}

	public String getId() {
		return id;
	}

	public AmazonEndpoint getEndpoint() {
		return endpoint;
	}

	/**
	 * Changed the selected endpoint to do requests for
	 * Amazon.
	 *
	 * @param	id			Amazon user / tracking id.
	 * @param	endpoint	Endpoint to use for Amazon requests.
	 */

	public void setEndpoint(String id, AmazonEndpoint endpoint) {
		this.id = id;
		this.endpoint = endpoint;
	}
}
