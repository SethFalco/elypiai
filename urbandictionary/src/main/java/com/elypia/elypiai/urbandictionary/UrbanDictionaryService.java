package com.elypia.elypiai.urbandictionary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UrbanDictionaryService {

    @GET("v0/define")
    Call<DefineResult> define(
        @Query("term") String term
    );
}
