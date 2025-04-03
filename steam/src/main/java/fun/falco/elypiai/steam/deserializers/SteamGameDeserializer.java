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

package fun.falco.elypiai.steam.deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import fun.falco.elypiai.steam.models.SteamGame;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class SteamGameDeserializer implements JsonDeserializer<List<SteamGame>> {

    @Override
    public List<SteamGame> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonElement games = json.getAsJsonObject().getAsJsonObject("response").getAsJsonArray("games");
        return new Gson().fromJson(games, typeOfT);
    }
}
