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

package org.elypia.elypiai.common.core;

import okhttp3.*;
import org.elypia.elypiai.common.core.ext.WrapperExtension;

import java.io.IOException;
import java.util.*;

/**
 * An abstract wrapper for sharing common code between all
 * API wrappers.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public abstract class ApiWrapper {

    /** A list of extensions enabled for this wrapper. */
    protected List<WrapperExtension> exts;

    /**
     * @param exts An optional list of extensions for this wrapper.
     */
    public ApiWrapper(WrapperExtension... exts) {
        this.exts = new ArrayList<>();

        if (exts != null)
            addExtensions(exts);
    }

    /**
     * @param exts A list of extentions to add to this wrapper.
     */
    public void addExtensions(WrapperExtension... exts) {
        this.exts.addAll(List.of(exts));
    }

    /**
     * A pre action to do before executing the request.
     *
     * @param request The request that was performed.
     * @return Non-null if one of the extensions are providing the response.
     * ie an external cache.
     */
    public Response pre(Request request) throws IOException {
        for (WrapperExtension ext : exts) {
            Response response = ext.pre(request);

            if (response != null)
                return response;
        }

        return null;
    }

    /**
     * A post action to do after executing a request, this should only
     * be called after an HTTP request, not obtaining a response from cache.
     *
     * @param request The request that was executed.
     * @param response The response returned from the API.
     */
    public Response post(Request request, Response response) throws IOException {
        for (WrapperExtension ext : exts)
            response = ext.post(request, response);

        return response;
    }
}
