package com.elypia.elypiai.twitch;

import java.util.*;

public class TwitchQuery {

    private List<Integer> userIds;
    private List<String> logins;
    private List<Integer> gameIds;

    public TwitchQuery() {
        userIds = new ArrayList<>();
        logins = new ArrayList<>();
        gameIds = new ArrayList<>();
    }

    public int getTotal() {
        return userIds.size() + logins.size() + gameIds.size();
    }

    public void addUserId(int id) {
        userIds.add(id);
    }

    public void addUsername(String name) {
        logins.add(name);
    }

    public void addGame(int id) {
        gameIds.add(id);
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public List<String> getUsernames() {
        return logins;
    }

    public List<Integer> getGameIds() {
        return gameIds;
    }
}
