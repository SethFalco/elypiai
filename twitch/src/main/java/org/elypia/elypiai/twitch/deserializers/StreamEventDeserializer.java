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
import org.elypia.elypiai.twitch.entity.TwitchStream;
import org.elypia.elypiai.twitch.notifier.event.StreamUpdateEvent;

import java.lang.reflect.Type;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class StreamEventDeserializer implements JsonDeserializer<StreamUpdateEvent>  {

    private final Gson GSON;

    public StreamEventDeserializer(Gson gson) {
        this.GSON = gson;
    }

    @Override
    public StreamUpdateEvent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray data = json.getAsJsonObject().getAsJsonArray("data");

        if (data.size() == 0)
            return new StreamUpdateEvent(null);

        JsonObject object = data.get(0).getAsJsonObject();
        return new StreamUpdateEvent(GSON.fromJson(object, TwitchStream.class));
    }
}
