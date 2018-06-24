package com.elypia.elypiai.urbandictionary;

import retrofit2.Call;
import retrofit2.http.*;

public interface UrbanDictionaryService {

    @GET("v0/define")
    Call<UrbanResult> define(@Query("term") String term);
}
