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

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fun.falco.elypiai.osu.models.Match;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class OsuMatchDeserializer implements JsonDeserializer<Match> {

    private final Gson GSON;

    public OsuMatchDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public Match deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();
        JsonObject match = object.getAsJsonObject("match");
        match.add("games", object.getAsJsonArray("games"));
        return GSON.fromJson(match, typeOfT);
    }
}
