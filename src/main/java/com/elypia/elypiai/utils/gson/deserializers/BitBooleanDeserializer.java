package com.elypia.elypiai.utils.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;

public class BitBooleanDeserializer implements JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return json.isJsonPrimitive() && json.getAsNumber().intValue() == 1;
    }
}
