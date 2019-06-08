package com.elypia.elypiai.cleverbot.impl;

import com.elypia.elypiai.cleverbot.CleverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CleverbotService {

    @GET("getreply")
    Call<CleverResponse> say(
        @Query("input") String input,
        @Query("cs") String cs,
        @Query("cb_settings_tweak1") Integer tweakOne,
        @Query("cb_settings_tweak2") Integer tweakTwo,
        @Query("cb_settings_tweak3") Integer tweakThree
    );
}
