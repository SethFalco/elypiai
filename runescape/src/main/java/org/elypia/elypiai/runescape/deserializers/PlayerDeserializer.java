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

package org.elypia.elypiai.runescape.deserializers;

import com.google.gson.*;
import org.elypia.elypiai.runescape.Player;
import org.elypia.elypiai.runescape.data.RuneScapeError;
import org.elypia.retropia.core.exceptions.FriendlyException;

import java.lang.reflect.Type;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class PlayerDeserializer implements JsonDeserializer<Player> {

    private final Gson gson;

    public PlayerDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();

        if (object.has("error")) {
            String error = object.get("error").getAsString();
            RuneScapeError rsError = RuneScapeError.get(error);

            switch (rsError) {
                case UNKNOWN: throw new FriendlyException(error, "An unknown error occurred which prevented us from getting the user.", true);
                case PROFILE_PRIVATE: throw new FriendlyException(error, "The user has their RuneMetrics profile set to private.", true);
                case NO_PROFILE: throw new FriendlyException(error, "The user doesn't exist.", true);
                case NOT_A_MEMBER: throw new FriendlyException(error, "The user is banned.", true);
                default: throw new IllegalStateException("This shouldn't be possible.");
            }
        }

        return gson.fromJson(json, Player.class);
    }
}
