package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.*;
import com.elypia.elypiai.amazon.impl.IAmazonService;
import com.elypia.elypiai.restutils.RestAction;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.net.URL;
import java.security.InvalidKeyException;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class Amazon {

	/**
	 * Validates if the {@link #getAccessKey() access key}
	 * is valid.
	 */
	public static final Pattern AMAZON_ACCESS_KEY = Pattern.compile("AKIA[IJ][A-Z\\d]{14}[AQ]");

	/**
	 * Validates if the {@link #getSecret() secret} is valid.
	 */
	public static final Pattern AMAZON_SECRET = Pattern.compile("(?i)[A-Z\\d/+]{40}");

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
	 * @throws IllegalArgumentException If invalid access key or secret is provided.
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
	 * @throws IllegalArgumentException If invalid access key or secret is provided.
	 */
	public Amazon(String accessKey, String secret, String id, AmazonEndpoint endpoint) throws InvalidKeyException {
		this(endpoint.getEndpoint(), accessKey, secret, id, endpoint);
	}

	public Amazon(URL baseUrl, String accessKey, String secret, String id, AmazonEndpoint endpoint) throws InvalidKeyException {
		this.accessKey = Objects.requireNonNull(accessKey);
		this.secret = Objects.requireNonNull(secret);
		this.id = Objects.requireNonNull(id);
		this.endpoint = Objects.requireNonNull(endpoint);

		if (!AMAZON_ACCESS_KEY.matcher(accessKey).matches())
			throw new IllegalArgumentException("The access key provided doesn't match the format expected.");

		if (!AMAZON_SECRET.matcher(secret).matches())
			throw new IllegalArgumentException("The secret doesn't match the format expected.");

		signer = new RequestSigner(secret);

		Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl.toString());
		retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());

		service = retrofitBuilder.build().create(IAmazonService.class);
	}

	public RestAction<AmazonResult> getItems(String product) {
		return getItems(product, DEFAULT_GROUPS);
	}

	public RestAction<AmazonResult> getItems(String product, AmazonGroup[] groups) {
		return getItems(product, groups, ProductIndex.ALL);
	}

	public RestAction<AmazonResult> getItems(String product, AmazonGroup[] groups, ProductIndex index) {
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
