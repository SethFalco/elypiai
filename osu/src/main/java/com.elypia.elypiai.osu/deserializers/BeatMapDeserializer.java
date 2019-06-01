package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.BeatMap;
import com.elypia.elypiai.osu.MapDifficulty;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class BeatMapDeserializer implements JsonDeserializer<List<BeatMap>> {

    private final Gson GSON;

    public BeatMapDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public List<BeatMap> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        List<BeatMap> maps = GSON.fromJson(array, typeOfT);

        for (int i = 0; i < maps.size(); i++) {
            JsonObject map = array.get(i).getAsJsonObject();
            maps.get(i).setDifficulty(context.deserialize(map, MapDifficulty.class));
        }

        return maps;
    }
}
