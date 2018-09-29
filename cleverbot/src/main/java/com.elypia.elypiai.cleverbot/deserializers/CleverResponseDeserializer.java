package com.elypia.elypiai.cleverbot.deserializers;

import com.elypia.elypiai.cleverbot.CleverResponse;
import com.elypia.elypiai.utils.Tuple;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.*;

public class CleverResponseDeserializer implements JsonDeserializer<CleverResponse> {

    private static Gson gson = new Gson();

    @Override
    public CleverResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        CleverResponse response = gson.fromJson(object, CleverResponse.class);

        List<Tuple<String, String>> interactions = new ArrayList<>();

        for (int i = 50; i > 0; i--) {
            String interaction = "interaction_" + i;
            String otherInteraction = interaction + "_other";

            if (object.has(otherInteraction))
                interactions.add(Tuple.of(object.get(interaction).getAsString(), object.get(otherInteraction).getAsString()));
        }

        response.setHistory(interactions);

        return response;
    }
}
