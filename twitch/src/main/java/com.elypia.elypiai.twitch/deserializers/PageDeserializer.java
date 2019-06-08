package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.TwitchPage;
import com.elypia.elypiai.twitch.entity.Stream;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class PageDeserializer implements JsonDeserializer<TwitchPage> {

    private static final Gson gson = new Gson();

    @Override
    public TwitchPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonElement data = object.getAsJsonArray("data");
        List<Stream> users = gson.fromJson(data, new TypeToken<List<Stream>>(){}.getType());
        String cursor = object.has("cursor") ? object.get("cursor").getAsString() : null;

        return new TwitchPage<>(users, cursor);
    }
}
