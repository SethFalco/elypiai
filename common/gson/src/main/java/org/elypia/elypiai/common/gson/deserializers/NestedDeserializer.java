/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.common.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class NestedDeserializer implements JsonDeserializer<Object> {

    private static final String OBJECT_ERROR = "Don't use nested deserializer when there is more than one nested object.";
    private static final String ARRAY_ERROR = "Don't use nested deserializer when there can be more than one element in the array.";

    private static Gson gson = new Gson();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            Set<String> keys = object.keySet();

            if (keys.size() > 1)
                throw new IllegalArgumentException(OBJECT_ERROR);

            String key = keys.iterator().next();
            return gson.fromJson(object.get(key), typeOfT);
        }

        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();

            if (array.size() > 1)
                throw new IllegalArgumentException(ARRAY_ERROR);

            return gson.fromJson(array.get(0), typeOfT);
        }

        return null;
    }
}
