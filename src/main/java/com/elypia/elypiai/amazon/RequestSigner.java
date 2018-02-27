package com.elypia.elypiai.amazon;

import org.apache.commons.codec.Charsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class RequestSigner {

	private static final String URI = "/onca/xml";
	private static final Charset charset = Charsets.UTF_8;

	private Mac mac;

	public RequestSigner(String secret) {
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(secret.getBytes(charset), mac.getAlgorithm()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String sign(AmazonEndpoint endpoint, Map<String, Object> queryParams) {
		StringBuilder builder = new StringBuilder();
		Iterator<Entry<String, Object>> iterator = queryParams.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();

			builder.append(String.format("%s=%s", urlEncode(entry.getKey()), urlEncode(entry.getValue())));

			if (iterator.hasNext())
				builder.append("&");
		}

		String toSign = String.format("GET\n%s\n%s\n%s", endpoint, URI, builder);

		byte[] hmac = mac.doFinal(toSign.getBytes(charset));
		String signature = urlEncode(Base64.getEncoder().encodeToString(hmac));

		return String.format("http://%s%s?%s&Signature=%s", endpoint, URI, builder, signature);
	}

	private String urlEncode(Object encode) {
		String string = encode.toString();

		try {
			string = URLEncoder.encode(encode.toString(), charset.toString());
			string = string.replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
			return string;
		} catch (UnsupportedEncodingException ex) {
			return string;
		}
	}
}
