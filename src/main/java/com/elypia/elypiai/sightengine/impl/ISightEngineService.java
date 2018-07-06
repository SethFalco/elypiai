package com.elypia.elypiai.sightengine.impl;

import com.elypia.elypiai.sightengine.NudityScore;
import retrofit2.Call;
import retrofit2.http.*;

public interface ISightEngineService {

    @GET("nudity.json")
    Call<NudityScore> detectNudity(@Query("url") String url);
}
