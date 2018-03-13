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
import java.io.InputStream;
import java.util.Objects;

public class ElyResponse {

    private Response response;
    private ResponseBody responseBody;
    private Call call;
    private String body;

    private JSONObject object;
    private JSONArray array;
    private Document document;

    public ElyResponse(Call call, Response response) {
        this.response = response;
        this.call = call;

        responseBody = response.body();
    }

    public JSONObject asJSONObject() {
        body = toString();

        if (object == null)
            object = new JSONObject(body);

        return object;
    }

    public JSONArray asJSONArray() {
        body = toString();

        if (array == null)
            array = new JSONArray(body);

        return array;
    }

    public Document asDocument() {
        return asDocument(Parser.htmlParser());
    }

    public Document asDocument(Parser parser) {
        Objects.requireNonNull(parser);

        if (document == null) {
            try (InputStream input = responseBody.byteStream()) {
                String uri = call.request().url().uri().toString();
                document = Jsoup.parse(input, "UTF-8", uri, parser);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return document;
    }

    @Override
    public String toString() {
        if (body == null) {
            try {
                body = responseBody.string();
                return body;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getHeader(String header) {
        return response.header(header);
    }
}
