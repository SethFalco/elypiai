package com.elypia.elypiai.common.core.ext;

import okhttp3.*;

import java.io.IOException;

public interface WrapperExtension {
    Response pre(Request request) throws IOException;
    Response post(Request request, Response response) throws IOException;
}
