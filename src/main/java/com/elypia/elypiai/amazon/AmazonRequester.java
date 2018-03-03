package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import com.elypia.elypiai.amazon.data.AmazonGroup;
import com.elypia.elypiai.amazon.data.AmazonIndex;
import com.elypia.elypiai.utils.ElyUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class AmazonRequester {

    private Amazon amazon;
    private String accessKey;
    private RequestSigner signer;

    public AmazonRequester(Amazon amazon) throws InvalidKeyException, NoSuchAlgorithmException {
        this.amazon = amazon;

        signer = new RequestSigner(amazon.getSecret());

        accessKey = amazon.getAccessKey();
    }

    /**
     * Searches Amazon with the selected endpoint
     * {@link Amazon#setEndpoint} and grabs the
     * url of the most relevant result.
     *
     * @param	product 	Product to search for.
     */

    public void getItem(String product, AmazonGroup[] groups, AmazonIndex index, Consumer<List<AmazonItem>> success, Consumer<UnirestException> failure) {
        String id = amazon.getId();
        AmazonEndpoint endpoint = amazon.getEndpoint();

        Map<String, Object> queryParams = new LinkedHashMap<>();
        queryParams.put("AWSAccessKeyId", accessKey);
        queryParams.put("AssociateTag", id);
        queryParams.put("Keywords", product);
        queryParams.put("Operation", "ItemSearch");
        queryParams.put("ResponseGroup", ElyUtils.join(",", groups));
        queryParams.put("SearchIndex", index.getApiName());
        queryParams.put("Service", "AWSECommerceService");
        queryParams.put("Timestamp", Instant.now());
        queryParams.put("Version", "2013-08-01");

        String url = signer.sign(endpoint, queryParams);

        Unirest.get(url).asStringAsync(new Callback<>() {

            @Override
            public void completed(HttpResponse<String> response) {
                Document document = Jsoup.parse(response.getBody(), url, Parser.xmlParser());

                List<AmazonItem> list = new ArrayList<>();

                if (document.getElementsByTag("Error").size() == 0) {
                    Elements items = document.getElementsByTag("Item");

                    items.forEach(element -> {
                        AmazonItem item = new AmazonItem(amazon, element);
                        list.add(item);
                    });
                }

                success.accept(list);
            }

            @Override
            public void failed(UnirestException ex) {
                failure.accept(ex);
            }

            @Override
            public void cancelled() {

            }
        });
    }
}
