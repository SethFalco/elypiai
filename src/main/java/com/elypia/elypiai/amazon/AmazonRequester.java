package com.elypia.elypiai.amazon;

import com.elypia.elypiai.amazon.data.AmazonEndpoint;
import com.elypia.elypiai.amazon.data.AmazonGroup;
import com.elypia.elypiai.amazon.data.AmazonIndex;
import com.elypia.elypiai.utils.ElyUtils;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.InvalidKeyException;
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

    /**
     * Create an instance of the request helper. This performs the HTTP
     * requests.
     *
     * @param amazon    Parent Amazon instance.
     * @throws InvalidKeyException  If the key provided is invalid or null.
     */

    public AmazonRequester(Amazon amazon) throws InvalidKeyException {
        this.amazon = amazon;

        signer = new RequestSigner(amazon.getSecret());

        accessKey = amazon.getAccessKey();
    }

    /**
     * Searches Amazon with the selected endpoint
     * {@link Amazon#setEndpoint} and grabs the
     * url of the most relevant result.
     *
     * @param	product Search query to find products.
     * @param   groups  What type of data Amazon should return.
     * @param   index   The department to search in.
     * @param   success What to perform on the result if succesful.
     * @param   failure What to do should the request fail, eg timeout.
     */

    public void getItems(String product, AmazonGroup[] groups, AmazonIndex index, Consumer<List<AmazonItem>> success, Consumer<IOException> failure) {
        String id = amazon.getId();
        AmazonEndpoint endpoint = amazon.getEndpoint();
        String[] groupString = ElyUtils.toStringArray(groups);

        Map<String, Object> queryParams = new LinkedHashMap<>();
        queryParams.put("AWSAccessKeyId", accessKey);
        queryParams.put("AssociateTag", id);
        queryParams.put("Keywords", product);
        queryParams.put("Operation", "ItemSearch");
        queryParams.put("ResponseGroup", String.join(",", groupString));
        queryParams.put("SearchIndex", index.getApiName());
        queryParams.put("Service", "AWSECommerceService");
        queryParams.put("Timestamp", Instant.now());
        queryParams.put("Version", "2013-08-01");

        String url = signer.sign(endpoint, queryParams);

        ElyRequest req = new ElyRequest(url);

        req.get(result -> {
            Document document = result.asDocument(Parser.xmlParser());

            List<AmazonItem> list = new ArrayList<>();

            if (document.getElementsByTag("Error").size() == 0) {
                Elements items = document.getElementsByTag("Item");

                items.forEach(element -> {
                    AmazonItem item = new AmazonItem(amazon, element);
                    list.add(item);
                });
            }

            success.accept(list);
        }, err -> {
            failure.accept(err);
        });
    }
}
