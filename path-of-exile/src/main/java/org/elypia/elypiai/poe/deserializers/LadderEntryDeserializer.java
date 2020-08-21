/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

package org.elypia.elypiai.poe.deserializers;

import com.google.gson.*;
import org.elypia.elypiai.poe.LadderEntry;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class LadderEntryDeserializer implements JsonDeserializer<List<LadderEntry>> {

    @Override
    public List<LadderEntry> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonArray array  = json.getAsJsonObject().getAsJsonArray("entries");
        return new Gson().fromJson(array, typeOfT);
    }
}
