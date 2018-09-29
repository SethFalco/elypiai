package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.*;
import com.elypia.elypiai.amazon.impl.IAmazonService;
import com.elypia.elypiai.restutils.RestAction;
import com.elypia.elypiai.utils.Regex;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.*;

public class Amazon {

	/**
	 * The default {@link AmazonGroup}s to request Amazon for
	 * getting product data.
	 */

	private final static AmazonGroup[] DEFAULT_GROUPS = {
		AmazonGroup.IMAGES,
		AmazonGroup.ITEM_ATTRIBUTES,
		AmazonGroup.OFFERS
	};

	private IAmazonService service;
	private RequestSigner signer;

	private String accessKey;
	private String secret;
	private String id;
	private AmazonEndpoint endpoint;

	/**
	 * Calls {@link #Amazon(String, String, String, AmazonEndpoint)}
	 * with AmazonEndpoint set to {@link AmazonEndpoint#US} by default if it
	 * ends with "20" otherwise {@link AmazonEndpoint#UK}.
	 *
	 * @param accessKey	Amazon Access Key obtained from AWS.
	 * @param secret	Amazon Secret obtained from AWS.
	 * @param id	Amazon Affiliate ID obtained from the Amazon Affiliate Programme.
	 * @throws InvalidKeyException	If an invalid key is provided.
	 */

	public Amazon(String accessKey, String secret, String id) throws InvalidKeyException {
		this(accessKey, secret, id, id.endsWith("20") ? AmazonEndpoint.US : AmazonEndpoint.UK);
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
		this(endpoint.getEndpoint(), accessKey, secret, id, endpoint);
	}

	public Amazon(String baseUrl, String accessKey, String secret, String id, AmazonEndpoint endpoint) throws InvalidKeyException {
		this.accessKey = Objects.requireNonNull(accessKey);
		this.secret = Objects.requireNonNull(secret);
		this.id = Objects.requireNonNull(id);
		this.endpoint = Objects.requireNonNull(endpoint);

		if (!Regex.AMAZON_ACCESS_KEY.matches(accessKey))
			throw new IllegalArgumentException("Invalid Amazon Access Key provided.");

		if (!Regex.AMAZON_SECRET.matches(secret))
			throw new IllegalArgumentException("Invalid Amazon Secret provided.");

		signer = new RequestSigner(secret);

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
		retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());

		service = retrofitBuilder.build().create(IAmazonService.class);
	}

	public RestAction<AmazonResult> getItems(String product) {
		return getItems(product, DEFAULT_GROUPS);
	}

	public RestAction<AmazonResult> getItems(String product, AmazonGroup[] groups) {
		return getItems(product, groups, AmazonIndex.ALL);
	}

	public RestAction<AmazonResult> getItems(String product, AmazonGroup[] groups, AmazonIndex index) {
		StringJoiner joiner = new StringJoiner(",");

		for (AmazonGroup g : groups)
			joiner.add(g.getName());

		Map<String, Object> queryParams = new LinkedHashMap<>();
		queryParams.put("AWSAccessKeyId", accessKey);
		queryParams.put("AssociateTag", id);
		queryParams.put("Keywords", product);
		queryParams.put("Operation", "ItemSearch");
		queryParams.put("ResponseGroup", joiner.toString());
		queryParams.put("SearchIndex", index.getName());
		queryParams.put("Service", "AWSECommerceService");
		queryParams.put("Timestamp", Instant.now());
		queryParams.put("Version", "2013-08-01");

		String signature = signer.sign(endpoint, queryParams);
		queryParams.put("Signature", signature);

		Call<AmazonResult> call = service.getItems(queryParams);
		return new RestAction<>(call);
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
}
