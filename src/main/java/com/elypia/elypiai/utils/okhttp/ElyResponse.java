package com.elypia.elypiai.utils.okhttp;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.IOException;
import java.util.Objects;

public class ElyResponse {

    private Response response;
    private ResponseBody responseBody;
    private Call call;
    private String body;

    private JSONObject object;
    private JSONArray array;
    private Document document;

    public ElyResponse(Call call, Response response) throws IOException {
        this.response = response;
        this.call = call;

        responseBody = response.body();
        body = responseBody.string();
    }

    public JSONObject asJSONObject() {
        if (object == null)
            object = new JSONObject(body);

        return object;
    }

    public JSONArray asJSONArray() {
        if (array == null)
            array = new JSONArray(body);

        return array;
    }

    public Document asDocument() {
        return asDocument(Parser.htmlParser());
    }

    public Document asDocument(Parser parser) {
        Objects.requireNonNull(parser);
        String uri = call.request().url().uri().toString();
        document = Jsoup.parse(body, uri, parser);

        return document;
    }

    public String asString() {
        return body;
    }

    public String getHeader(String header) {
        return response.header(header);
    }
}
