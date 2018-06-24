package com.elypia.elypiai.utils.okhttp;

import okhttp3.OkHttpClient;

public class Requester {

    public OkHttpClient client;

    public Requester() {
        client = new OkHttpClient();
    }

    public <T> void execute(Request request) {

    }
}
