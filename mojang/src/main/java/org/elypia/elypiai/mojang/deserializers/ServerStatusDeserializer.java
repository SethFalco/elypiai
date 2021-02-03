/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.mojang.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import org.elypia.elypiai.mojang.models.MojangServer;
import org.elypia.elypiai.mojang.models.ServerStatus;

import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.3.0
 */
public class ServerStatusDeserializer implements JsonDeserializer<Map<MojangServer, ServerStatus>> {

    @Override
    public Map<MojangServer, ServerStatus> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!json.isJsonArray())
            throw new JsonParseException("Expecting json to be array");

        final Map<MojangServer, ServerStatus> result = new EnumMap<>(MojangServer.class);

        final JsonArray array = json.getAsJsonArray();

        for (JsonElement element : array) {
            if (!element.isJsonObject())
                throw new JsonParseException("Expecting only JsonObjects inside of JsonArray");

            JsonObject object = element.getAsJsonObject();

            Set<Map.Entry<String, JsonElement>> entrySet = object.entrySet();

            if (entrySet.size() != 1)
                throw new JsonParseException("Expecting JsonObject to have only 1 JsonElement inside");

            Map.Entry<String, JsonElement> entry = entrySet.iterator().next();

            final String server = entry.getKey();
            final String status = entry.getValue().getAsString();

            result.put(MojangServer.get(server), ServerStatus.get(status));
        }

        return result;
    }
}
