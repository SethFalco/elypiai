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

package org.elypia.elypiai.orna;

import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.common.core.ext.WrapperExtension;
import org.elypia.elypiai.common.gson.GsonService;
import org.elypia.elypiai.orna.entities.*;
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
    private static URL BASE_URL;

    static {
        try {
            BASE_URL = new URL("https://orna.guide/api/v1/");
        } catch (MalformedURLException ex) {
            logger.error(Elypiai.MALFORMED, ex);
        }
    }

    private OrnaService service;

    public Orna() {
        this((WrapperExtension[])null);
    }

    public Orna(WrapperExtension... exts) {
        this(BASE_URL, exts);
    }

    public Orna(URL baseUrl, WrapperExtension... exts) {
        super(exts);

        service = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(RequestService.withExtensionInterceptor(this))
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
