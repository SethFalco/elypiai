package com.elypia.elypiai.amazon.data;

import com.elypia.elypiai.amazon.Product;

import java.net.*;

public enum AmazonEndpoint {

	UNKNOWN(null),
	US(".com"),
	BRAZIL(".com.br"),
	CANADA(".ca"),
	CHINA(".cn"),
	GERMANY(".de"),
	SPAIN(".es"),
	FRANCE(".fr"),
	INDIA(".in"),
	ITALY(".it"),
	JAPAN(".co.jp"),
	MEXICO(".com.mx"),
	UK(".co.uk");

	private final String TLD;

	AmazonEndpoint(String tld) {
		this.TLD = tld;
	}

	public String getTld() {
		return TLD;
	}

	/**
	 * Do <strong>NOT</strong> use this to to display product data
	 * results, use the URL provided with {@link Product} for monetization.
	 *
	 * @return	The Amazon homepage for this endpoint / country.
	 */
	public String getShoppingUrl() {
		return String.format("https://amazon%s/", TLD);
	}

	public URL getEndpoint() {
		try {
			return new URL("https://" + toString() + "/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String toString() {
		return "webservices.amazon" + TLD;
	}
}
