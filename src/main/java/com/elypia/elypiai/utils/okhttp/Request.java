package com.elypia.elypiai.utils.okhttp;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * This is the request builder class which abstracts and simplifies building requests
 * for the use of Elypiai.
 *
 * @param <T> The type of response object we're expecting to receive.
 */

public class Request {

    /**
     * The URL builder for building the URL to request from.
     */

    private HttpUrl.Builder urlBuilder;
    private okhttp3.Request.Builder reqBuilder;
    private RequestBody body;

    public Request(String url, String route, Object... routeParameters) {
        this(url + route, routeParameters);
    }

    public Request(String url, Object... routeParameters) {
        while (url.contains("/:")) {
//            url.replaceFirst("(?i)/:[A-Z]", routeParameters)
        }


        urlBuilder = HttpUrl.parse(url).newBuilder();
        reqBuilder = new okhttp3.Request.Builder();
    }

    public <T> void addParam(String key, T value) {
        urlBuilder.addQueryParameter(key, value.toString());
    }

    public void addHeader(String key, String value) {
        reqBuilder.addHeader(key, value);
    }

    public void setFormData(JSONObject object) {
        MediaType type = MediaType.parse("application/json; charset=utf-8");
        body = RequestBody.create(type, object.toString());
    }

    public void setFormData(String data) {
        MediaType type = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
        body = RequestBody.create(type, data);
    }

    public void get(Consumer<Response> success, Consumer<IOException> failure) {
        reqBuilder.get();
        build(success, failure);
    }

    public void head(Consumer<Response> success, Consumer<IOException> failure) {
        reqBuilder.head();
        build(success, failure);
    }

    public void post(Consumer<Response> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.post(body);
        build(success, failure);
    }

    public void delete(Consumer<Response> success, Consumer<IOException> failure) {
        if (body != null)
            reqBuilder.delete(body);
        else
            reqBuilder.delete();

        build(success, failure);
    }

    public void put(Consumer<Response> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.put(body);
        build(success, failure);
    }

    public void patch(Consumer<Response> success, Consumer<IOException> failure) {
        Objects.requireNonNull(body);

        reqBuilder.patch(body);
        build(success, failure);
    }

    public void build(Consumer<Response> success, Consumer<IOException> failure) {
        HttpUrl url = urlBuilder.build();
        okhttp3.Request req = reqBuilder.url(url).build();

//        CLIENT.newCall(req).enqueue(new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException ex) {
//                failure.accept(ex);
//            }
//
//            @Override
//            public void onResponse(Call call, okhttp3.Response response) {
//                try {
//                    Response resp = new Response(call, response);
//                    success.accept(resp);
//                } catch (IOException ex) {
//                    onFailure(call, ex);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }
}

