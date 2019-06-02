package com.elypia.elypiai.common.gson;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Store global singleton {@link GsonConverterFactory} instance.
 * This improves performance between APIs.
 */
public class GsonService {

    private static GsonConverterFactory factory;

    /**
     * @return Get the global {@link GsonConverterFactory} instance.
     */
    public static GsonConverterFactory getInstance() {
        if (factory == null)
            factory = GsonConverterFactory.create();

        return factory;
    }
}
