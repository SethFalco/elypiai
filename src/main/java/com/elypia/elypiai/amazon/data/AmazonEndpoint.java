package com.elypia.elypiai.amazon.data;

public enum AmazonEndpoint {

	US("amazon.com"),
	BRAZIL("amazon.com.br"),
	CANADA("amazon.ca"),
	CHINA("amazon.cn"),
	GERMANY("amazon.de"),
	SPAIN("amazon.es"),
	FRANCE("amazon.fr"),
	INDIA("amazon.in"),
	ITALY("amazon.it"),
	JAPAN("amazon.co.jp"),
	MEXICO("amazon.com.mx"),
	UK("amazon.co.uk");

	private String url;

	AmazonEndpoint(String url) {
		this.url = url;
	}

	/**
	 * @return	The Amazon homepage for this endpoint / country.
	 */

	public String getShoppingUrl() {
		return String.format("https://%s/", url);
	}

	/**
	 * @param asin	The ASIN ID of the product.
	 * @return	The product URL.
	 */

    public String getProductUrl(String asin) {
        return String.format(getShoppingUrl() + "dp/" + asin);
    }

	@Override
	public String toString() {
		return "webservices." + url;
	}
}
