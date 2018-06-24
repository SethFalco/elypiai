package com.elypia.elypiai.utils.okhttp.deserializers;

import com.elypia.elypiai.urbandictionary.UrbanResultType;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UrbanResultTypeDeserializer implements JsonDeserializer<UrbanResultType> {

    @Override
    public UrbanResultType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return UrbanResultType.getByName(json.getAsString());
    }
}
