package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import com.elypia.elypiai.amazon.data.AmazonGroup;
import com.elypia.elypiai.amazon.data.AmazonIndex;
import com.elypia.elypiai.utils.Regex;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Amazon {

	/**
	 * The default {@link AmazonGroup}s to request Amazon for
	 * while getting product data.
	 */

	private final static AmazonGroup[] DEFAULT_GROUPS = {
		AmazonGroup.IMAGES,
		AmazonGroup.ITEM_ATTRIBUTES,
		AmazonGroup.OFFERS
	};

	private String accessKey;
	private String secret;
	private String id;

	/**
	 * Request helper to make API calls for us.
	 */

	private AmazonRequester requester;

	/**
	 *	The selected endpoint to make requests.
	 */

	private AmazonEndpoint endpoint;

	/**
	 * Calls {@link #Amazon(String, String, String, AmazonEndpoint)}
	 * with AmazonEndpoint set to {@link AmazonEndpoint#US} by default.
	 *
	 * @param accessKey	Amazon Access Key obtained from AWS.
	 * @param secret	Amazon Secret obtained from AWS.
	 * @param id	Amazon Affiliate ID obtained from the Amazon Affiliate Programme.
	 * @throws InvalidKeyException	If an invalid key is provided.
	 */

	public Amazon(String accessKey, String secret, String id) throws InvalidKeyException {
		this(accessKey, secret, id, AmazonEndpoint.US);
	}

	/**
	 * Creates a new instance of Amazon for the id and endpoint provided.
	 *
	 * @param accessKey Amazon Access Key obtained from AWS.
	 * @param secret Amazon Secret obtained from AWS.
	 * @param id Amazon Affiliate ID obtained from the Amazon Affiliate Programme.
	 * @param endpoint The {@link AmazonEndpoint} / service this ID is associated with.
	 * @throws InvalidKeyException	If an invalid key is provided.
	 */

	public Amazon(String accessKey, String secret, String id, AmazonEndpoint endpoint) throws InvalidKeyException {
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

	public void getItems(String product, Consumer<List<AmazonItem>> success, Consumer<IOException> failure) {
		getItems(product, DEFAULT_GROUPS, success, failure);
	}

	public void getItems(String product, AmazonGroup[] groups, Consumer<List<AmazonItem>> success, Consumer<IOException> failure) {
		getItems(product, groups, AmazonIndex.ALL, success, failure);
	}

	public void getItems(String product, AmazonGroup[] groups, AmazonIndex index, Consumer<List<AmazonItem>> success, Consumer<IOException> failure) {
		requester.getItems(product, groups, index, success, failure);
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
