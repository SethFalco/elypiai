package com.elypia.elypiai.amazon.impl;

import com.elypia.elypiai.amazon.AmazonResult;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface IAmazonService {

    @GET("onca/xml")
    Call<AmazonResult> getItems(
        @QueryMap Map<String, Object> params
    );
}
