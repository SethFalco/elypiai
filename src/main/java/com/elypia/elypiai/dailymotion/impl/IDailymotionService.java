package com.elypia.elypiai.dailymotion.impl;

import com.elypia.elypiai.dailymotion.DailymotionVideo;
import retrofit2.Call;
import retrofit2.http.*;

public interface IDailymotionService {

    @GET("video/{id}")
    Call<DailymotionVideo> getVideo(
        @Path("id") String id
    );
}
