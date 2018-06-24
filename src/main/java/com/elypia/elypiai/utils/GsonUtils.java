package com.elypia.elypiai.utils;

import com.elypia.elypiai.urbandictionary.UrbanResultType;
import com.elypia.elypiai.utils.okhttp.deserializers.*;
import com.google.gson.*;

public class GsonUtils {

    private static Gson gson;

    public static Gson getGsonSingleton() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(UrbanResultType.class, new UrbanResultTypeDeserializer());
            gsonBuilder.registerTypeAdapter(String.class, new StringDeserializer());
            gson = gsonBuilder.create();
        }

        return gson;
    }
}
