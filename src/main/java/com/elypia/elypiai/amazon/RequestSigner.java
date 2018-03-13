package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class RequestSigner {

	private static final String URI = "/onca/xml";
	private static final Charset charset = StandardCharsets.UTF_8;

	private Mac mac;

	public RequestSigner(String secret) throws InvalidKeyException {
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes(charset), mac.getAlgorithm()));
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
	}

	public String sign(AmazonEndpoint endpoint, Map<String, Object> queryParams) {
		StringBuilder builder = new StringBuilder();
		Iterator<Entry<String, Object>> iterator = queryParams.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = urlEncode(entry.getKey());
			Object value = urlEncode(entry.getValue());

			builder.append(String.format("%s=%s", key, value));

			if (iterator.hasNext())
				builder.append("&");
		}

		String toSign = String.format("GET\n%s\n%s\n%s", endpoint, URI, builder);

		byte[] hmac = mac.doFinal(toSign.getBytes(charset));
		String signature = urlEncode(Base64.getEncoder().encodeToString(hmac));

		return String.format("http://%s%s?%s&Signature=%s", endpoint, URI, builder, signature);
	}

	private <T> String urlEncode(T encode) {
		Objects.requireNonNull(encode, "Can not encode a null value.");

		String string = encode.toString();

		try {
			string = URLEncoder.encode(encode.toString(), charset.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		string = string.replace("+", "%20");
		string = string.replace("*", "%2A");
		string = string.replace("%7E", "~");

		return string;
	}
}
