package com.elypia.elypiai.steam;

import com.elypia.elypiai.steam.data.SteamEndpoint;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class SteamRequester {

    private Steam steam;

    public SteamRequester(Steam steam) {
        this.steam = steam;
    }

    public void getUsers(long[] ids, Consumer<Collection<SteamUser>> success, Consumer<IOException> failure) {
        int length = ids.length;
        String[] idStrings = new String[length];

        for (int i = 0; i < length; i++)
            idStrings[i] = String.valueOf(ids[i]);

        String endpoint = SteamEndpoint.GET_USER.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);
        req.addParam("key", steam.getApiKey());
        req.addParam("steamids", String.join(",", idStrings));

        req.get(result -> {
            Collection<SteamUser> users = new ArrayList<>();
            JSONObject object = result.asJSONObject();
            JSONArray players = object.getJSONObject("response").getJSONArray("players");

            for (int i = 0; i < players.length(); i++) {
                JSONObject userObj = players.getJSONObject(i);
                SteamUser user = new SteamUser(steam, userObj);
                users.add(user);
            }

            success.accept(users);
        }, failure);
    }

    /**
     * Obtain ID of a user from the username to utilise the API.
     *
     * @param vanityUrl The name you'd find at the end of a users custom URL.
     * @param success What to do with the ID if the request it succeful.
     * @param failure What to do if someone goes wrong, eg timeout.
     */

    public void getIdFromVanityUrl(String vanityUrl, Consumer<Long> success, Consumer<IOException> failure) {
        String endpoint = SteamEndpoint.GET_STEAM_ID.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);
        req.addParam("key", steam.getApiKey());
        req.addParam("vanityUrl", vanityUrl);

        req.get(result -> {
            Long id = null;
            JSONObject object = result.asJSONObject();
            JSONObject response = object.getJSONObject("response");

            if (response.getInt("success") == 1)
                id = response.getLong("steamid");

            success.accept(id);
        }, failure);
    }

    public void getLibrary(SteamUser user, Consumer<List<SteamGame>> success, Consumer<IOException> failure) {
        String endpoint = SteamEndpoint.GET_LIB.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);
        req.addParam("steamid", user.getId());
        req.addParam("format", "json");
        req.addParam("key", steam.getApiKey());
        req.addParam("include_appinfo", 1);
        req.addParam("include_played_free_games", 1);

        req.get(result -> {
            JSONArray array = result.asJSONObject().getJSONObject("response").getJSONArray("games");

            List<SteamGame> library = new ArrayList<>();

            for (int i = 0; i < array.length(); i++)
                library.add(new SteamGame(user, array.getJSONObject(i)));

            Collections.sort(library);
            success.accept(library);
        }, failure);
    }
}
