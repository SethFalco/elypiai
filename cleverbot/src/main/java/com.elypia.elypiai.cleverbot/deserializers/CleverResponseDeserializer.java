package com.elypia.elypiai.cleverbot.deserializers;

import com.elypia.elypiai.cleverbot.CleverResponse;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CleverResponseDeserializer implements JsonDeserializer<CleverResponse> {

    private static final Gson GSON = new Gson();

    @Override
    public CleverResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        JsonArray jsonInteractions = new JsonArray();

        for (int i = 50; i > 0; i--) {
            String interaction = "interaction_" + i;
            String otherInteraction = interaction + "_other";

            if (object.has(otherInteraction)) {
                JsonObject jsonInteraction = new JsonObject();
                jsonInteraction.addProperty("say", object.get(interaction).getAsString());
                jsonInteraction.addProperty("response", object.get(otherInteraction).getAsString());

                jsonInteractions.add(jsonInteraction);
            }
        }

        object.add("interactions", jsonInteractions);
        return GSON.fromJson(object, CleverResponse.class);
    }
}
