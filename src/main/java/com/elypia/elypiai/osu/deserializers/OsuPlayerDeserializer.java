package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.*;
import com.elypia.elypiai.utils.gson.deserializers.UtcDateDeserializer;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class OsuPlayerDeserializer implements JsonDeserializer<OsuPlayer> {

    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new UtcDateDeserializer(Osu.OSU_DATE_FORMAT)).create();

    @Override
    public OsuPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();

        if (array.size() == 0)
            return null;

        JsonObject object = array.get(0).getAsJsonObject();
        return GSON.fromJson(object, typeOfT);
    }
}
