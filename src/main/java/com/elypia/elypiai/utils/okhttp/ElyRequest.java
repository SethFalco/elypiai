package com.elypia.elypiai.utils.okhttp;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class ElyRequest {

    public static final OkHttpClient CLIENT = new OkHttpClient();

    private HttpUrl.Builder urlBuilder;
    private Request.Builder reqBuilder;
    private RequestBody body;

    private String url;
    private Map<String, String> params;
    private Map<String, String> headers;

    public ElyRequest(ElyRequest req) {
        this(req, req.url);
    }

    public ElyRequest(ElyRequest req, String url, Object... routes) {
        this(url, routes);

        req.params.forEach(this::addParam);
        req.headers.forEach(this::addHeader);
    }

    public ElyRequest(String url, Object... routes) {
        if (routes.length > 0)
            this.url = String.format(url, routes);

        urlBuilder = HttpUrl.parse(this.url).newBuilder();
        reqBuilder = new Request.Builder();
        params = new HashMap<>();
        headers = new HashMap<>();
    }

    public <T> void addParam(String key, T value) {
        urlBuilder.addQueryParameter(key, value.toString());
        params.put(key, value.toString());
    }

    public void addHeader(String key, String value) {
        reqBuilder.addHeader(key, value);
        headers.put(key, value);
    }

    public void setFormData(JSONObject object) {
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        body = RequestBody.create(type, object.toString());
    }

    public void setFormData(String data) {
        MediaType type = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        body = RequestBody.create(type, data);
    }

    public void get(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        reqBuilder.get();
        build(success, failure);
    }

    public void head(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        reqBuilder.head();
        build(success, failure);
    }

    public void post(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.post(body);
        build(success, failure);
    }

    public void delete(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        if (body != null)
            reqBuilder.delete(body);
        else
            reqBuilder.delete();

        build(success, failure);
    }

    public void put(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.put(body);
        build(success, failure);
    }

    public void patch(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.patch(body);
        build(success, failure);
    }

    public void build(Consumer<ElyResponse> success, Consumer<IOException> failure) {
        HttpUrl url = urlBuilder.build();
        Request req = reqBuilder.url(url).build();

        CLIENT.newCall(req).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException ex) {
                failure.accept(ex);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ElyResponse resp = new ElyResponse(call, response);
                    success.accept(resp);
                } catch (IOException ex) {
                    onFailure(call, ex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

