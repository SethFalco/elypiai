package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class TwitchUserDeserializer implements JsonDeserializer<List<TwitchUser>> {

    private static Gson gson = new Gson();

    private Twitch twitch;

    public TwitchUserDeserializer(Twitch twitch) {
        this.twitch = twitch;
    }

    @Override
    public List<TwitchUser> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement data = json.getAsJsonObject().getAsJsonArray("data");
        List<TwitchUser> users = gson.fromJson(data, typeOfT);

        users.forEach(user -> user.setTwitch(twitch));

        return users;
    }
}
