package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class BeatMapDeserializer implements JsonDeserializer<List<BeatMap>> {

    private static Gson gson = new GsonBuilder().setDateFormat(Osu.OSU_DATE_FORMAT).create();

    @Override
    public List<BeatMap> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        List<BeatMap> maps = gson.fromJson(array, typeOfT);

        for (int i = 0; i < maps.size(); i++) {
            JsonObject map = array.get(i).getAsJsonObject();
            maps.get(i).setDifficulty(gson.fromJson(map, MapDifficulty.class));
        }

        return maps;
    }
}
