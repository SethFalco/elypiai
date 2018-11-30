package com.elypia.elypiai.steam.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class SteamDateDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonPrimitive() || json.isJsonNull())
            throw new JsonParseException("Object is not a JSON primitive.");

        return new Date(json.getAsLong());
    }
}
