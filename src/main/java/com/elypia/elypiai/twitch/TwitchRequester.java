package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.data.TwitchEndpoint;
import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class TwitchRequester {

    private Twitch twitch;

    public TwitchRequester(Twitch twitch) {
        this.twitch = twitch;
    }

    /**
     * Recieved Twitch information on a user, this only recieved general
     * information.
     */

    public void getUsers(String[] usernames, Consumer<Collection<TwitchUser>> success, Consumer<IOException> failure) {
        if (usernames.length > 100)
            throw new IllegalArgumentException("Can only request a maximum of 100 users at a time.");

        String endpoint = TwitchEndpoint.GET_USERS.getEndpoint();

        ElyRequest req = new ElyRequest(endpoint);
        req.addHeader("Client-Id", twitch.getApiKey());

        for (String username : usernames)
            req.addParam("login", username);

        req.get(result -> {
            Collection<TwitchUser> collection = new ArrayList<>();
            JSONObject object = result.asJSONObject();
            JSONArray users = object.getJSONArray("data");

            for (int i = 0; i < users.length(); i++) {
                JSONObject user = users.getJSONObject(i);
                TwitchUser twitchUser = new TwitchUser(twitch, user);
                collection.add(twitchUser);
            }

            success.accept(collection);
        }, failure);
    }
}
