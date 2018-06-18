package com.elypia.elypiai.utils.okhttp;

import com.elypia.elypiai.utils.JsonUtils;
import com.google.gson.*;
import okhttp3.*;
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

    private JsonObject object;
    private JsonArray array;
    private Document document;

    public ElyResponse(Call call, Response response) throws IOException {
        this.response = response;
        this.call = call;

        responseBody = response.body();
        body = responseBody.string();
    }

    public JsonObject asJsonObject() {
        if (object == null)
            object = JsonUtils.toJsonObject(body);

        return object;
    }

    public JsonArray asJsonArray() {
        if (array == null)
            array = JsonUtils.toJsonArray(body);

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
