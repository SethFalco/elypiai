package com.elypia.elypiai.common.core.ext;

import com.elypia.elypiai.common.core.ApiWrapper;
import okhttp3.*;

import java.io.IOException;

public class ExtensionInterceptor implements Interceptor {

    private final ApiWrapper wrapper;

    public ExtensionInterceptor(ApiWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = wrapper.pre(request);

        if (response != null)
            return response;

        response = chain.proceed(request);
        return wrapper.post(request, response);
    }
}
