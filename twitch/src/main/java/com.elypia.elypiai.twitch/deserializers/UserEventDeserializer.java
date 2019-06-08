package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.entity.User;
import com.elypia.elypiai.twitch.notifier.event.UserUpdatedEvent;
import com.google.gson.*;

import java.lang.reflect.Type;

public class UserEventDeserializer implements JsonDeserializer<UserUpdatedEvent>  {

    private final Gson GSON;

    public UserEventDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public UserUpdatedEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().getAsJsonArray("data");

        if (data.size() == 0)
            return new UserUpdatedEvent(null);

        JsonObject object = data.get(0).getAsJsonObject();
        return new UserUpdatedEvent(GSON.fromJson(object, User.class));
    }
}
