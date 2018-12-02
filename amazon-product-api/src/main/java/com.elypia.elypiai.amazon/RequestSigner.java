package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.*;
import java.security.*;
import java.util.*;

public class RequestSigner {

	private static final String URI = "/onca/xml";
	private static final Charset charset = StandardCharsets.UTF_8;

	private Mac mac;

	protected RequestSigner(String secret) throws InvalidKeyException {
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes(charset), mac.getAlgorithm()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	protected String sign(AmazonEndpoint endpoint, Map<String, Object> queryParams) {
		StringJoiner joiner = new StringJoiner("&");

		queryParams.forEach((key, value) -> {
			joiner.add(urlEncode(key) + "=" + urlEncode(value));
		});

		String toSign = String.format("GET\n%s\n%s\n%s", endpoint, URI, joiner);

		byte[] hmac = mac.doFinal(toSign.getBytes(charset));
		return urlEncode(Base64.getEncoder().encodeToString(hmac));
	}

	private <T> String urlEncode(T encode) {
		String string = encode.toString();

		try {
			string = URLEncoder.encode(encode.toString(), charset.toString());
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}

		string = string.replace("+", "%20");
		string = string.replace("*", "%2A");
		string = string.replace("%7E", "~");

		return string;
	}
}
