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
import org.elypia.elypiai.twitch.notifier.event.FollowEvent;

import java.lang.reflect.Type;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class FollowEventDeserializer implements JsonDeserializer<FollowEvent>  {

    private final Gson GSON;

    public FollowEventDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public FollowEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().getAsJsonArray("data");
        JsonObject object = data.get(0).getAsJsonObject();
        return GSON.fromJson(object, typeOfT);
    }
}
