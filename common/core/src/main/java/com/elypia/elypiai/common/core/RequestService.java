package com.elypia.elypiai.common.core;

import com.elypia.elypiai.common.core.ext.ExtensionInterceptor;
import okhttp3.OkHttpClient;

/**
 * Store global singleton HTTP client instance.
 * This improves performance between APIs.
 */
public class RequestService {

    private static OkHttpClient client;

    /**
     * This should be used when some runtime modifications
     * are required such as custom inceptors.
     *
     * @return Get a builder that will share the same core
     * as the global {@link OkHttpClient} client.
     */
    public static OkHttpClient.Builder getBuilder() {
        if (client == null)
            client = new OkHttpClient();

        return client.newBuilder();
    }

    public static OkHttpClient withExtensionInterceptor(ApiWrapper wrapper) {
        return getBuilder().addInterceptor(new ExtensionInterceptor(wrapper)).build();
    }
}
