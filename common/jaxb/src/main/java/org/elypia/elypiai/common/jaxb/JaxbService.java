/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.common.jaxb;

import retrofit2.converter.jaxb.JaxbConverterFactory;

/**
 * Store global singleton {@link JaxbConverterFactory} instance.
 * This improves performance between APIs.
 *
 * @author seth@elypia.org (Seth Falco)
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
