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

package fun.falco.elypiai.osu.deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fun.falco.elypiai.osu.models.BeatMap;
import fun.falco.elypiai.osu.models.MapDifficulty;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class BeatMapDeserializer implements JsonDeserializer<List<BeatMap>> {

    private final Gson gson;

    public BeatMapDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<BeatMap> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonArray array = json.getAsJsonArray();
        List<BeatMap> maps = gson.fromJson(array, typeOfT);

        for (int i = 0; i < maps.size(); i++) {
            JsonObject map = array.get(i).getAsJsonObject();
            maps.get(i).setDifficulty(context.deserialize(map, MapDifficulty.class));
        }

        return maps;
    }
}
