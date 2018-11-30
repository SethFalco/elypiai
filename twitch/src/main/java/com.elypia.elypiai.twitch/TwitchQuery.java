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

    public void addUserId(Integer... ids) {
        userIds.addAll(List.of(ids));
    }

    public void addUsername(String names) {
        logins.addAll(List.of(names));
    }

    public void addGame(Integer... ids) {
        gameIds.addAll(List.of(ids));
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public List<String> getUsernames() {
        return logins;
    }

    public List<Integer> getGames() {
        return gameIds;
    }
}
