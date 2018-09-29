package com.elypia.elypiai.nanowrimo.impl;

import com.elypia.elypiai.nanowrimo.NanoUser;
import retrofit2.Call;
import retrofit2.http.*;

public interface INanowrimoService {

    @GET("wordcount_api/wc/{name}")
    Call<NanoUser> getUser(
            @Path("name") String name
    );

    @GET("wordcount_api/wc/{name}")
    Call<NanoUser> getUserHistory(
            @Path("name") String name
    );

    @Multipart
    @PUT("api/wordcount")
    Call<String> setWordCount(
            @Part("hash") String hash,
            @Part("name") String name,
            @Part("wordcount") int wordcount
    );
}
