package com.elypia.elypiai.edamam;

import com.elypia.elypiai.edamam.data.EdamamEndpoint;
import com.elypia.elypiai.utils.okhttp.Request;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class EdamamRequester {

    private Edamam edamam;

    public EdamamRequester(Edamam edamam) {
        this.edamam = edamam;
    }

    public void search(String q, Consumer<List<Ingredient>> success, Consumer<IOException> failure) {
        EdamamEndpoint endpoint = EdamamEndpoint.SEARCH;
        Request req = new Request(endpoint.getEndpoint());

        req.addParam("app_id", edamam.getAppId());
        req.addParam("app_key", edamam.getAppKey());
        req.addParam("q", q);

        req.get(resp -> {

        }, ex -> failure.accept(ex));
    }
}
