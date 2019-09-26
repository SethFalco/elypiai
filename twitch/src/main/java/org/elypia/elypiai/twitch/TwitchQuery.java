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

package org.elypia.elypiai.twitch;

import java.util.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
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
