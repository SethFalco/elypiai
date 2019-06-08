package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.notifier.event.FollowEvent;
import com.google.gson.*;

import java.lang.reflect.Type;

public class FollowEventDeserializer implements JsonDeserializer<FollowEvent>  {

    private final Gson GSON;

    public FollowEventDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public FollowEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().getAsJsonArray("data");
        JsonObject object = data.get(0).getAsJsonObject();
        return GSON.fromJson(object, typeOfT);
    }
}
