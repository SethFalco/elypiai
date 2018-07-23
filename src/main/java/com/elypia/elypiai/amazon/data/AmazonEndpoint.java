package com.elypia.elypiai.amazon.data;

import com.elypia.elypiai.amazon.AmazonItem;

public enum AmazonEndpoint {

	US("com"),
	BRAZIL("com.br"),
	CANADA("ca"),
	CHINA("cn"),
	GERMANY("de"),
	SPAIN("es"),
	FRANCE("fr"),
	INDIA("in"),
	ITALY("it"),
	JAPAN("co.jp"),
	MEXICO("com.mx"),
	UK("co.uk");

	private final String TLD;

	AmazonEndpoint(String tld) {
		this.TLD = tld;
	}

	public String getTld() {
		return TLD;
	}

	/**
	 * Do <strong>NOT</strong> use this to to display product data
	 * results, use the URL provided with {@link AmazonItem} for monetization.
	 *
	 * @return	The Amazon homepage for this endpoint / country.
	 */

	public String getShoppingUrl() {
		return String.format("https://amazon.%s/", TLD);
	}

	public String getEndpoint() {
		return "https://" + toString() + "/";
	}

	@Override
	public String toString() {
		return "webservices.amazon." + TLD;
	}
}
