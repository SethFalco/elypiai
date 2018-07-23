package com.elypia.elypiai.deviantart.impl;

import com.elypia.elypiai.deviantart.Deviation;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IDeviantArtService {

    @GET("rss.xml?type=deviation")
    Call<List<Deviation>> getDeviations(
        @Query("q") String q
    );
}
