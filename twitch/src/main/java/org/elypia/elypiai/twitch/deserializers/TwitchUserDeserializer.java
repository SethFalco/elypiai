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

package org.elypia.elypiai.twitch.deserializers;

import com.google.gson.*;
import org.elypia.elypiai.twitch.Twitch;
import org.elypia.elypiai.twitch.entity.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class TwitchUserDeserializer implements JsonDeserializer<List<User>> {

    private static final Gson GSON = new Gson();

    private Twitch twitch;

    public TwitchUserDeserializer(Twitch twitch) {
        this.twitch = twitch;
    }

    @Override
    public List<User> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement data = json.getAsJsonObject().getAsJsonArray("data");
        List<User> users = GSON.fromJson(data, typeOfT);
        users.forEach(user -> user.setTwitch(twitch));
        return users;
    }
}
