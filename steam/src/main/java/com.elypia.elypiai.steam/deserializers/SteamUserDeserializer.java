package com.elypia.elypiai.steam.deserializers;

import com.elypia.elypiai.steam.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class SteamUserDeserializer implements JsonDeserializer<List<SteamUser>> {

    private static Gson gson = new Gson();

    private Steam steam;

    public SteamUserDeserializer(Steam steam) {
        this.steam = steam;
    }

    @Override
    public List<SteamUser> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray players = json.getAsJsonObject().getAsJsonObject("response").getAsJsonArray("players");
        List<SteamUser> users = gson.fromJson(players, typeOfT);

        for (int i = 0; i < users.size(); i++) {
            users.get(i).setSteam(steam);

            JsonObject player = players.get(i).getAsJsonObject();

            if (player.has("gameextrainfo"))
                users.get(i).setSession(gson.fromJson(player, GameSession.class));
        }

        return users;
    }
}
