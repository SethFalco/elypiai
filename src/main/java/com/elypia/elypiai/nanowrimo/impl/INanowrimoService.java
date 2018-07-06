package com.elypia.elypiai.nanowrimo.impl;

import com.elypia.elypiai.nanowrimo.NanoUser;
import retrofit2.Call;
import retrofit2.http.*;

public interface INanowrimoService {

    @GET("wordcount_api/wc/{name}")
    Call<NanoUser> getUser(
        @Path("name") String name
    );

    @FormUrlEncoded
    @PUT("api/wordcount")
    Call<String> setWordCount(
        @Field("hash") String hash,
        @Field("name") String name,
        @Field("wordcount") int wordcount
    );
}
