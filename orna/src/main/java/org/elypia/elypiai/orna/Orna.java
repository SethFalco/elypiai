/*
 * Copyright 2019-2020 Elypia CIC
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

package org.elypia.elypiai.orna;

import org.elypia.elypiai.orna.entities.*;
import org.elypia.retropia.core.*;
import org.elypia.retropia.core.extensions.WrapperExtension;
import org.elypia.retropia.core.requests.RestAction;
import org.elypia.retropia.gson.GsonService;
import org.slf4j.*;
import retrofit2.*;

import java.net.*;
import java.util.List;

/**
 * Unofficial API wrapper for Java for Orna Guide.
 *
 * @see <a href="https://orna.guide/">https://orna.guide/</a>
 * @author seth@elypia.org (Seth Falco)
 */
public class Orna extends ApiWrapper {

    /** Endpoint that stores all static resources for Orna Guide. */
    private static final String RESOURCE_URL = "https://orna.guide/static/orna/img/";

    private static final Logger logger = LoggerFactory.getLogger(Orna.class);

    /**
     * The default URL we call too. <br>
     * Should never throw {@link MalformedURLException} as this
     * is a manually hardcoded URL.
     */
    private static URL baseUrl;

    static {
        try {
            baseUrl = new URL("https://orna.guide/api/v1/");
        } catch (MalformedURLException ex) {
            logger.error("Hardcoded URL is malformed, please specify a valid URL as a parameter.", ex);
        }
    }

    private OrnaService service;

    public Orna() {
        this(new WrapperExtension[0]);
    }

    public Orna(WrapperExtension... exts) {
        this(baseUrl, exts);
    }

    public Orna(URL baseUrl, WrapperExtension... exts) {
        super(exts);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(RequestService.withExtensions(exts))
            .addConverterFactory(GsonService.getInstance())
            .build()
            .create(OrnaService.class);
    }

    public RestAction<List<Monster>> getMonsters() {
        Call<List<Monster>> call = service.getMonsters();
        return new RestAction<>(call);
    }

    /**
     * @param image The image attribute of a {@link AbstractEntity}
     * that has an image associated with it.
     * @return The URL that can display the image for this entity.
     */
    public static String getStaticResource(String image) {
        return RESOURCE_URL + image;
    }
}
