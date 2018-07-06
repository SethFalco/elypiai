package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class OsuPlayerDeserializer implements JsonDeserializer<OsuPlayer> {

    private static Gson gson = new GsonBuilder().setDateFormat(Osu.OSU_DATE_FORMAT).create();

    @Override
    public OsuPlayer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();

        if (array.size() == 0)
            return null;

        JsonObject object = array.get(0).getAsJsonObject();
        return gson.fromJson(object, typeOfT);
    }
}
