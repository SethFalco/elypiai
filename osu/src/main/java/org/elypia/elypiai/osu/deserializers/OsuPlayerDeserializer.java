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

package org.elypia.elypiai.osu.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.elypia.elypiai.osu.models.Player;

import java.lang.reflect.Type;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class OsuPlayerDeserializer implements JsonDeserializer<Player> {

    private final Gson gson;

    public OsuPlayerDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonArray array = json.getAsJsonArray();

        if (array.size() == 0) {
            return null;
        }

        JsonObject object = array.get(0).getAsJsonObject();
        return gson.fromJson(object, typeOfT);
    }
}
