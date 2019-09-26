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
import com.google.gson.reflect.TypeToken;
import org.elypia.elypiai.twitch.TwitchPage;
import org.elypia.elypiai.twitch.entity.TwitchStream;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class PageDeserializer implements JsonDeserializer<TwitchPage> {

    private static final Gson gson = new Gson();

    @Override
    public TwitchPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();
        JsonElement data = object.getAsJsonArray("data");
        List<TwitchStream> users = gson.fromJson(data, new TypeToken<List<TwitchStream>>(){}.getType());
        String cursor = object.has("cursor") ? object.get("cursor").getAsString() : null;

        return new TwitchPage<>(users, cursor);
    }
}
