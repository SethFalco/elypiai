package com.elypia.elypiai.utils;

import com.elypia.elypiai.urbandictionary.*;
import com.elypia.elypiai.utils.okhttp.deserializers.*;
import com.google.gson.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static Gson gson;

    public static <T> T createService(String baseUrl, Class<T> clazz) {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(UrbanResultType.class, new UrbanResultTypeDeserializer());
            gsonBuilder.registerTypeAdapter(String.class, new StringDeserializer());
            gson = gsonBuilder.create();
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(clazz);
    }
}
