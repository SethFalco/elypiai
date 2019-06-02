package com.elypia.elypiai.nanowrimo.impl;

import com.elypia.elypiai.nanowrimo.Writer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NanowrimoService {

    @GET("wordcount_api/wc/{name}")
    Call<Writer> getUser(
        @Path("name") String name
    );

    @GET("wordcount_api/wchistory/{name}")
    Call<Writer> getUserHistory(
        @Path("name") String name
    );
}
