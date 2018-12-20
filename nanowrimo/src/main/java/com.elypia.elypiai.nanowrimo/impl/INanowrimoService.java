package com.elypia.elypiai.nanowrimo.impl;

import com.elypia.elypiai.nanowrimo.Writer;
import retrofit2.Call;
import retrofit2.http.*;

public interface INanowrimoService {

    @GET("wordcount_api/wc/{name}")
    Call<Writer> getUser(
        @Path("name") String name
    );

    @GET("wordcount_api/wc/{name}")
    Call<Writer> getUserHistory(
        @Path("name") String name
    );
}
