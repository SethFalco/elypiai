package com.elypia.elypiai.test;

import com.elypia.elypiai.amazon.AmazonEndpoint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmazonTest {

	//////////////////////////////////////////////////////////////////////
	//				Ensure each endpoint URL is still correct.			//
	//////////////////////////////////////////////////////////////////////

	@Test
	public void checkUSEndpoint() {
		String expected = "webservices.amazon.com";
		String actual = AmazonEndpoint.US.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkBrazilEndpoint() {
		String expected = "webservices.amazon.com.br";
		String actual = AmazonEndpoint.BRAZIL.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkCanadaEndpoint() {
		String expected = "webservices.amazon.ca";
		String actual = AmazonEndpoint.CANADA.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkChinaEndpoint() {
		String expected = "webservices.amazon.cn";
		String actual = AmazonEndpoint.CHINA.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkGermanyEndpoint() {
		String expected = "webservices.amazon.de";
		String actual = AmazonEndpoint.GERMANY.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkSpainEndpoint() {
		String expected = "webservices.amazon.es";
		String actual = AmazonEndpoint.SPAIN.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkFranceEndpoint() {
		String expected = "webservices.amazon.fr";
		String actual = AmazonEndpoint.FRANCE.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkIndiaEndpoint() {
		String expected = "webservices.amazon.in";
		String actual = AmazonEndpoint.INDIA.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkItalyEndpoint() {
		String expected = "webservices.amazon.it";
		String actual = AmazonEndpoint.ITALY.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkJapanEndpoint() {
		String expected = "webservices.amazon.co.jp";
		String actual = AmazonEndpoint.JAPAN.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkMexicoEndpoint() {
		String expected = "webservices.amazon.com.mx";
		String actual = AmazonEndpoint.MEXICO.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void checkUKEndpoint() {
		String expected = "webservices.amazon.co.uk";
		String actual = AmazonEndpoint.UK.toString();

		assertEquals(expected, actual);
	}

	//////////////////////////////////////////////////////////////////////
	//			Ensure shopping url per endpoint is still correct.		//
	//////////////////////////////////////////////////////////////////////

	@Test
	public void checkUSShoppingUrl() {
		String expected = "https://amazon.com/";
		String actual = AmazonEndpoint.US.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkBrazilShoppingUrl() {
		String expected = "https://amazon.com.br/";
		String actual = AmazonEndpoint.BRAZIL.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkCanadaShoppingUrl() {
		String expected = "https://amazon.ca/";
		String actual = AmazonEndpoint.CANADA.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkChinaShoppingUrl() {
		String expected = "https://amazon.cn/";
		String actual = AmazonEndpoint.CHINA.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkGermanyShoppingUrl() {
		String expected = "https://amazon.de/";
		String actual = AmazonEndpoint.GERMANY.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkSpainShoppingUrl() {
		String expected = "https://amazon.es/";
		String actual = AmazonEndpoint.SPAIN.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkFranceShoppingUrl() {
		String expected = "https://amazon.fr/";
		String actual = AmazonEndpoint.FRANCE.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkIndiaShoppingUrl() {
		String expected = "https://amazon.in/";
		String actual = AmazonEndpoint.INDIA.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkItalyShoppingUrl() {
		String expected = "https://amazon.it/";
		String actual = AmazonEndpoint.ITALY.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkJapanShoppingUrl() {
		String expected = "https://amazon.co.jp/";
		String actual = AmazonEndpoint.JAPAN.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkMexicoShoppingUrl() {
		String expected = "https://amazon.com.mx/";
		String actual = AmazonEndpoint.MEXICO.getShoppingUrl();

		assertEquals(expected, actual);
	}

	@Test
	public void checkUKShoppingUrl() {
		String expected = "https://amazon.co.uk/";
		String actual = AmazonEndpoint.UK.getShoppingUrl();

		assertEquals(expected, actual);
	}
}
