package com.elypia.elypiai.test;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AmazonTest {

	@Test
	public void endpointStrings() {
		assertAll("Endpoint Strings",
			() -> assertEquals("webservices.amazon.com", AmazonEndpoint.US.toString()),
			() -> assertEquals("webservices.amazon.com.br", AmazonEndpoint.BRAZIL.toString()),
			() -> assertEquals("webservices.amazon.ca", AmazonEndpoint.CANADA.toString()),
			() -> assertEquals("webservices.amazon.cn", AmazonEndpoint.CHINA.toString()),
			() -> assertEquals("webservices.amazon.de", AmazonEndpoint.GERMANY.toString()),
			() -> assertEquals("webservices.amazon.es", AmazonEndpoint.SPAIN.toString()),
			() -> assertEquals("webservices.amazon.fr", AmazonEndpoint.FRANCE.toString()),
			() -> assertEquals("webservices.amazon.in", AmazonEndpoint.INDIA.toString()),
			() -> assertEquals("webservices.amazon.it", AmazonEndpoint.ITALY.toString()),
			() -> assertEquals("webservices.amazon.co.jp", AmazonEndpoint.JAPAN.toString()),
			() -> assertEquals("webservices.amazon.com.mx", AmazonEndpoint.MEXICO.toString()),
			() -> assertEquals("webservices.amazon.co.uk", AmazonEndpoint.UK.toString())
		);
	}

	@Test
	public void shoppingUrls() {
		assertAll("Endpoint Strings",
			() -> assertEquals("https://amazon.com/", AmazonEndpoint.US.getShoppingUrl()),
			() -> assertEquals("https://amazon.com.br/", AmazonEndpoint.BRAZIL.getShoppingUrl()),
			() -> assertEquals("https://amazon.ca/", AmazonEndpoint.CANADA.getShoppingUrl()),
			() -> assertEquals("https://amazon.cn/", AmazonEndpoint.CHINA.getShoppingUrl()),
			() -> assertEquals("https://amazon.de/", AmazonEndpoint.GERMANY.getShoppingUrl()),
			() -> assertEquals("https://amazon.es/", AmazonEndpoint.SPAIN.getShoppingUrl()),
			() -> assertEquals("https://amazon.fr/", AmazonEndpoint.FRANCE.getShoppingUrl()),
			() -> assertEquals("https://amazon.in/", AmazonEndpoint.INDIA.getShoppingUrl()),
			() -> assertEquals("https://amazon.it/", AmazonEndpoint.ITALY.getShoppingUrl()),
			() -> assertEquals("https://amazon.co.jp/", AmazonEndpoint.JAPAN.getShoppingUrl()),
			() -> assertEquals("https://amazon.com.mx/", AmazonEndpoint.MEXICO.getShoppingUrl()),
			() -> assertEquals("https://amazon.co.uk/", AmazonEndpoint.UK.getShoppingUrl())
		);
	}
}
