/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.cleverbot.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.elypia.elypiai.cleverbot.CleverResponse;

import java.lang.reflect.Type;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class CleverResponseDeserializer implements JsonDeserializer<CleverResponse> {

    private static final Gson GSON = new Gson();

    @Override
    public CleverResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
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
