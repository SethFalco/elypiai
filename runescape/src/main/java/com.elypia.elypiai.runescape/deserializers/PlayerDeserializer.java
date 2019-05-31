package com.elypia.elypiai.runescape.deserializers;

import com.elypia.elypiai.common.FriendlyException;
import com.elypia.elypiai.runescape.Player;
import com.elypia.elypiai.runescape.data.RuneScapeError;
import com.google.gson.*;

import java.lang.reflect.Type;

public class PlayerDeserializer implements JsonDeserializer<Player> {

    private final Gson gson;

    public PlayerDeserializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Player deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
