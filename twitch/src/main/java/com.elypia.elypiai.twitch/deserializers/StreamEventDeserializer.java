package com.elypia.elypiai.twitch.deserializers;

import com.elypia.elypiai.twitch.entity.Stream;
import com.elypia.elypiai.twitch.notifier.event.StreamUpdateEvent;
import com.google.gson.*;

import java.lang.reflect.Type;

public class StreamEventDeserializer implements JsonDeserializer<StreamUpdateEvent>  {

    private final Gson GSON;

    public StreamEventDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public StreamUpdateEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().getAsJsonArray("data");

        if (data.size() == 0)
            return new StreamUpdateEvent(null);

        JsonObject object = data.get(0).getAsJsonObject();
        return new StreamUpdateEvent(GSON.fromJson(object, Stream.class));
    }
}
