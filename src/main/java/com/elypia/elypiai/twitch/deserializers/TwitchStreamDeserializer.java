package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.TwitchStream;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class TwitchStreamDeserializer implements JsonDeserializer<List<TwitchStream>> {

    private static Gson gson = new Gson();

    @Override
    public List<TwitchStream> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        JsonElement data = object.getAsJsonArray("data");
        List<TwitchStream> streams = gson.fromJson(data, typeOfT);

        JsonElement cursor = object.getAsJsonObject("pagination").get("cursor");

        if (cursor != null)
            streams.forEach(stream -> stream.setCursor(cursor.getAsString()));

        return streams;
    }
}
