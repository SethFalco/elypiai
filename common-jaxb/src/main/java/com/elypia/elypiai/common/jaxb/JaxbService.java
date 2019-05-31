package com.elypia.elypiai.common.jaxb;

import retrofit2.converter.jaxb.JaxbConverterFactory;

/**
 * Store global singleton {@link JaxbConverterFactory} instance.
 * This improves performance between APIs.
 */
public class JaxbService {

    private static JaxbConverterFactory factory;

    /**
     * @return Get the global {@link JaxbConverterFactory} instance.
     */
    public static JaxbConverterFactory getInstance() {
        if (factory == null)
            factory = JaxbConverterFactory.create();

        return factory;
    }
}
