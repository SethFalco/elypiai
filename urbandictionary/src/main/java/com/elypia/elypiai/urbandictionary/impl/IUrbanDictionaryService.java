package com.elypia.elypiai.urbandictionary.impl;

import com.elypia.elypiai.urbandictionary.UrbanResult;
import retrofit2.Call;
import retrofit2.http.*;

public interface IUrbanDictionaryService {

    @GET("v0/define")
    Call<UrbanResult> define(
        @Query("term") String term
    );
}
