package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.data.OsuMod;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class OsuModDeserializer implements JsonDeserializer<List<OsuMod>> {

    @Override
    public List<OsuMod> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        long l = json.getAsLong();
        return OsuMod.get(l);
    }
}
