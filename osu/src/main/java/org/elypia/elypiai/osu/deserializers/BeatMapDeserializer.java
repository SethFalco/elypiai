/*
 * Copyright 2019-2020 Elypia CIC
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

import com.google.gson.*;
import org.elypia.elypiai.osu.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class BeatMapDeserializer implements JsonDeserializer<List<BeatMap>> {

    private final Gson GSON;

    public BeatMapDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public List<BeatMap> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray array = json.getAsJsonArray();
        List<BeatMap> maps = GSON.fromJson(array, typeOfT);

        for (int i = 0; i < maps.size(); i++) {
            JsonObject map = array.get(i).getAsJsonObject();
            maps.get(i).setDifficulty(context.deserialize(map, MapDifficulty.class));
        }

        return maps;
    }
}
