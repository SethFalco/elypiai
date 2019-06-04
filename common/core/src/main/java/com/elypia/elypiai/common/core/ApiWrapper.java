package com.elypia.elypiai.common.core;

import com.elypia.elypiai.common.core.ext.WrapperExtension;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

/**
 * An abstract wrapper for sharing common code between all
 * API wrappers.
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
