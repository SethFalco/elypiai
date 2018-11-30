package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class TwitchUserDeserializer implements JsonDeserializer<List<User>> {

    private static final Gson GSON = new Gson();

    private Twitch twitch;

    public TwitchUserDeserializer(Twitch twitch) {
        this.twitch = twitch;
    }

    @Override
    public List<User> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement data = json.getAsJsonObject().getAsJsonArray("data");
        List<User> users = GSON.fromJson(data, typeOfT);
        users.forEach(user -> user.setTwitch(twitch));

        return users;
    }
}
