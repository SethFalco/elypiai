package com.elypia.elypiai.deviantart;

import com.elypia.elypiai.deviantart.impl.IDeviantArtService;
import com.elypia.elypiai.utils.okhttp.RestAction;
import retrofit2.*;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.util.List;

public class DeviantArt {

    private static final String BASE_URL = "http://api.urbandictionary.com/";

    private IDeviantArtService service;

    public DeviantArt() {
        this(BASE_URL);
    }

    public DeviantArt(String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
        retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());

        service = retrofitBuilder.build().create(IDeviantArtService.class);
    }

    public RestAction<List<Deviation>> getDeviation(String query) {
        Call<List<Deviation>> call = service.getDeviations(query);
        return new RestAction<>(call);
    }
}
