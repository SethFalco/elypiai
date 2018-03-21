package com.elypia.elypiai.test;

import com.elypia.elypiai.amazon.Amazon;
import com.elypia.elypiai.amazon.AmazonItem;
import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import com.elypia.elypiai.amazon.data.AmazonGroup;
import com.elypia.elypiai.amazon.data.AmazonIndex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class AmazonTest {

	@Test
	public void invalidAmazon() {
		assertThrows(IllegalArgumentException.class, () -> new Amazon("fake key", "fake secret", "id", AmazonEndpoint.US));
		assertThrows(IllegalArgumentException.class, () -> new Amazon("AKIAJAKAC6MNKA34345A", "fake", "id", AmazonEndpoint.US));
		assertThrows(IllegalArgumentException.class, () -> new Amazon("fake key", "jz+FRGuufOCxQLJNGRHOUcKzLe8TZPi5p0CXVDED", "id", AmazonEndpoint.US));
	}

	@Test
	public void changeEndpoint() throws InvalidKeyException {
		Amazon amazon = new Amazon("AKIAJAKAC6MNKA34345A", "jz+FRGuufOCxQLJNGRHOUcKzLe8TZPi5p0CXVDED", "youset21-20");

		assertEquals("youset21-20", amazon.getId());
		assertEquals(AmazonEndpoint.US, amazon.getEndpoint());

		amazon.setEndpoint("elypia-21", AmazonEndpoint.UK);

		assertEquals("elypia-21", amazon.getId());
		assertEquals(AmazonEndpoint.UK, amazon.getEndpoint());
	}

	@Test
	public void parseItem() throws InvalidKeyException {
		Amazon amazon = new Amazon("AKIAJAKAC6MNKA34345A", "jz+FRGuufOCxQLJNGRHOUcKzLe8TZPi5p0CXVDED", "youset21-20");

		String xml = "<Item><ASIN>B01FJLA9IM</ASIN><ParentASIN>B0719HXT7M</ParentASIN><DetailPageURL>https://www.amazon.com/Intel-i7-6950X-Processor-Extreme-BX80671I76950X/dp/B01FJLA9IM?psc=1&SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=165953&creativeASIN=B01FJLA9IM</DetailPageURL><ItemLinks><ItemLink><Description>Technical Details</Description><URL>https://www.amazon.com/Intel-i7-6950X-Processor-Extreme-BX80671I76950X/dp/tech-data/B01FJLA9IM?SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>Add To Baby Registry</Description><URL>https://www.amazon.com/gp/registry/baby/add-item.html?asin.0=B01FJLA9IM&SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>Add To Wedding Registry</Description><URL>https://www.amazon.com/gp/registry/wedding/add-item.html?asin.0=B01FJLA9IM&SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>Add To Wishlist</Description><URL>https://www.amazon.com/gp/registry/wishlist/add-item.html?asin.0=B01FJLA9IM&SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>Tell A Friend</Description><URL>https://www.amazon.com/gp/pdp/taf/B01FJLA9IM?SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>All Customer Reviews</Description><URL>https://www.amazon.com/review/product/B01FJLA9IM?SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink><ItemLink><Description>All Offers</Description><URL>https://www.amazon.com/gp/offer-listing/B01FJLA9IM?SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</URL></ItemLink></ItemLinks><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage><ImageSets><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/41RbzAmPg7L.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51kblSpxpcL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/41CSWqJAJlL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/41-E-TIovTL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51vjqNupb1L.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51MOTjmtg2L.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51qk29idAiL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"variant\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/41DrwvDc01L.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet><ImageSet Category=\"primary\"><SwatchImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL30_.jpg</URL><Height Units=\"pixels\">30</Height><Width Units=\"pixels\">30</Width></SwatchImage><SmallImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></SmallImage><ThumbnailImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL75_.jpg</URL><Height Units=\"pixels\">75</Height><Width Units=\"pixels\">75</Width></ThumbnailImage><TinyImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL110_.jpg</URL><Height Units=\"pixels\">110</Height><Width Units=\"pixels\">110</Width></TinyImage><MediumImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL._SL160_.jpg</URL><Height Units=\"pixels\">160</Height><Width Units=\"pixels\">160</Width></MediumImage><LargeImage><URL>https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL.jpg</URL><Height Units=\"pixels\">500</Height><Width Units=\"pixels\">500</Width></LargeImage></ImageSet></ImageSets><ItemAttributes><Binding>Personal Computers</Binding><Brand>Intel</Brand><Color>gray</Color><EAN>0735858318044</EAN><EANList><EANListElement>0735858318044</EANListElement></EANList><Feature>10 Core/20 Thread</Feature><Feature>LGA 2011-v3</Feature><Feature>4 Channels DDR4 2400; one DIMM per Channel</Feature><Feature>140W Tdp; Up to 40 Lanes PCIe 3.0 (2x16+1x8).</Feature><Feature>Please Note: Kindly refer the Comparison Chart which is highly essential before ordering.</Feature><ItemDimensions><Height Units=\"hundredths-inches\">393</Height><Length Units=\"hundredths-inches\">456</Length><Weight Units=\"Hundredths Pounds\">40</Weight><Width Units=\"hundredths-inches\">161</Width></ItemDimensions><Label>Intel</Label><ListPrice><Amount>174300</Amount><CurrencyCode>USD</CurrencyCode><FormattedPrice>$1,743.00</FormattedPrice></ListPrice><Manufacturer>Intel</Manufacturer><Model>BX80671I76950X</Model><MPN>BX80671I76950X</MPN><PackageDimensions><Height Units=\"hundredths-inches\">200</Height><Length Units=\"hundredths-inches\">610</Length><Weight Units=\"Hundredths Pounds\">45</Weight><Width Units=\"hundredths-inches\">460</Width></PackageDimensions><PackageQuantity>1</PackageQuantity><PartNumber>BX80671I76950X</PartNumber><Platform>Windows</Platform><ProductGroup>Personal Computer</ProductGroup><ProductTypeName>COMPUTER_PROCESSOR</ProductTypeName><Publisher>Intel</Publisher><Studio>Intel</Studio><Title>Intel Boxed Core i7-6950X Processor Extreme Edition (25M Cache, up to 3.50 GHz) 3.0 10 BX80671I76950X</Title><UPC>735858318044</UPC><UPCList><UPCListElement>735858318044</UPCListElement></UPCList><Warranty>Limited warranty; 3 years warranty</Warranty></ItemAttributes><OfferSummary><LowestNewPrice><Amount>148900</Amount><CurrencyCode>USD</CurrencyCode><FormattedPrice>$1,489.00</FormattedPrice></LowestNewPrice><LowestUsedPrice><Amount>109900</Amount><CurrencyCode>USD</CurrencyCode><FormattedPrice>$1,099.00</FormattedPrice></LowestUsedPrice><TotalNew>33</TotalNew><TotalUsed>3</TotalUsed><TotalCollectible>0</TotalCollectible><TotalRefurbished>0</TotalRefurbished></OfferSummary><Offers><TotalOffers>1</TotalOffers><TotalOfferPages>1</TotalOfferPages><MoreOffersUrl>https://www.amazon.com/gp/offer-listing/B01FJLA9IM?SubscriptionId=AKIAJBZMRHR4PXIUPDTA&tag=youset21-20&linkCode=xm2&camp=2025&creative=386001&creativeASIN=B01FJLA9IM</MoreOffersUrl><Offer><OfferAttributes><Condition>New</Condition></OfferAttributes><OfferListing><OfferListingId>HeS3Jxy%2FkCmfXRdHIFw67py7o7x5cFBV1u7YM9K20bJfRHXIgw8Vo19HZd%2BY1Y%2BRi1V6jS%2BiMtjvJPIVemciA%2FzIh5mgVl3610X2Ub8EXQUJr9g8gv4%2B%2B7s%2FsKVZx0yYyjZjyjF%2BgK7z4l9hDJhaVYq4VpA0jz%2BU</OfferListingId><Price><Amount>153198</Amount><CurrencyCode>USD</CurrencyCode><FormattedPrice>$1,531.98</FormattedPrice></Price><AmountSaved><Amount>21102</Amount><CurrencyCode>USD</CurrencyCode><FormattedPrice>$211.02</FormattedPrice></AmountSaved><PercentageSaved>12</PercentageSaved><Availability>Usually ships in 24 hours</Availability><AvailabilityAttributes><AvailabilityType>now</AvailabilityType><MinimumHours>0</MinimumHours><MaximumHours>0</MaximumHours></AvailabilityAttributes><IsEligibleForSuperSaverShipping>1</IsEligibleForSuperSaverShipping><IsEligibleForPrime>1</IsEligibleForPrime></OfferListing></Offer></Offers></Item>";
		Document document = Jsoup.parse(xml, "", Parser.xmlParser());
		AmazonItem item = new AmazonItem(amazon, document);

		assertAll("Endpoint Strings",
			() -> assertNotNull(item.getAmazon()),
			() -> assertEquals(AmazonEndpoint.US, item.getEndpoint()),
			() -> assertEquals("B01FJLA9IM", item.getAsin()),
			() -> assertEquals("B0719HXT7M", item.getParentAsin()),
			() -> assertEquals("https://amazon.com/dp/B01FJLA9IM?tag=youset21-20", item.getUrl()),
			() -> assertEquals(1743.00, item.getPrice()),
			() -> assertEquals("$1,743.00", item.getPriceString()),
			() -> assertEquals(Currency.getInstance("USD").getSymbol(), item.getCurrency().getSymbol()),
			() -> assertEquals("https://images-na.ssl-images-amazon.com/images/I/51xql2lzYgL.jpg", item.getImage())
		);
	}

	@Test
	public void departmentStrings() {
		assertAll("Department Strings",
			() -> assertEquals("All", AmazonIndex.ALL.getApiName()),
			() -> assertEquals("ArtsAndCrafts", AmazonIndex.ARTS_AND_CRAFTS.getApiName())
		);
	}

	@Test
	public void groupStrings() {
		assertAll("Group Strings",
			() -> assertEquals("Images", AmazonGroup.IMAGES.getApiName()),
			() -> assertEquals("Accessories", AmazonGroup.ACCESSORIES.getApiName())
		);
	}

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
			() -> assertEquals("https://amazon.com", AmazonEndpoint.US.getShoppingUrl()),
			() -> assertEquals("https://amazon.com.br", AmazonEndpoint.BRAZIL.getShoppingUrl()),
			() -> assertEquals("https://amazon.ca", AmazonEndpoint.CANADA.getShoppingUrl()),
			() -> assertEquals("https://amazon.cn", AmazonEndpoint.CHINA.getShoppingUrl()),
			() -> assertEquals("https://amazon.de", AmazonEndpoint.GERMANY.getShoppingUrl()),
			() -> assertEquals("https://amazon.es", AmazonEndpoint.SPAIN.getShoppingUrl()),
			() -> assertEquals("https://amazon.fr", AmazonEndpoint.FRANCE.getShoppingUrl()),
			() -> assertEquals("https://amazon.in", AmazonEndpoint.INDIA.getShoppingUrl()),
			() -> assertEquals("https://amazon.it", AmazonEndpoint.ITALY.getShoppingUrl()),
			() -> assertEquals("https://amazon.co.jp", AmazonEndpoint.JAPAN.getShoppingUrl()),
			() -> assertEquals("https://amazon.com.mx", AmazonEndpoint.MEXICO.getShoppingUrl()),
			() -> assertEquals("https://amazon.co.uk", AmazonEndpoint.UK.getShoppingUrl())
		);
	}
}
