package com.elypia.elypiai.osu.deserializers;

import com.elypia.elypiai.osu.Player;
import com.google.gson.*;

import java.lang.reflect.Type;

public class OsuPlayerDeserializer implements JsonDeserializer<Player> {

    private final Gson GSON;

    public OsuPlayerDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();

        if (array.size() == 0)
            return null;

        JsonObject object = array.get(0).getAsJsonObject();
        return GSON.fromJson(object, typeOfT);
    }
}
