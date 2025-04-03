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

package org.elypia.elypiai.steam.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.elypia.elypiai.steam.Steam;
import org.elypia.elypiai.steam.models.GameSession;
import org.elypia.elypiai.steam.models.SteamUser;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class SteamUserDeserializer implements JsonDeserializer<List<SteamUser>> {

    private static Gson gson = new Gson();

    private Steam steam;

    public SteamUserDeserializer(Steam steam) {
        this.steam = steam;
    }

    @Override
    public List<SteamUser> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonArray players = json.getAsJsonObject().getAsJsonObject("response").getAsJsonArray("players");
        List<SteamUser> users = gson.fromJson(players, typeOfT);

        for (int i = 0; i < users.size(); i++) {
            users.get(i).setSteam(steam);

            JsonObject player = players.get(i).getAsJsonObject();

            if (player.has("gameextrainfo")) {
                users.get(i).setCurrentlyPlaying(gson.fromJson(player, GameSession.class));
            }
        }

        return users;
    }
}
