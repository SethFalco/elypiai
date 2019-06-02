package com.elypia.elypiai.common.core;

import okhttp3.OkHttpClient;

/**
 * Store global singleton HTTP client instance.
 * This improves performance between APIs.
 */
public class RequestService {

    private static OkHttpClient client;

    /**
     * @return Get the global {@link OkHttpClient} instance.
     */
    public static OkHttpClient getInstance() {
        if (client == null)
            client = new OkHttpClient();

        return client;
    }

    /**
     * This should be used when some runtime modifications
     * are required such as custom inceptors.
     *
     * @return Get a builder that will share the same core
     * as the global {@link OkHttpClient} client.
     */
    public static OkHttpClient.Builder getBuilder() {
        return getInstance().newBuilder();
    }
}
