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

import org.elypia.elypiai.common.core.*;
import org.elypia.elypiai.twitch.entity.TwitchStream;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class StreamPaginator implements RestPaginator<TwitchStream> {

    private Twitch twitch;
    private TwitchQuery query;
    private int limit;
    private String cursor;

    public StreamPaginator(Twitch twitch, TwitchQuery query, int limit) {
        this.twitch = twitch;
        this.query = query;
        this.limit = limit;
    }

    @Override
    public List<TwitchStream> next() throws IOException {
        Call<TwitchPage<TwitchStream>> streamers = twitch.getService().getStreams(
            query.getUserIds(),
            query.getUsernames(),
            query.getGames(),
            limit,
            cursor
        );

        TwitchPage<TwitchStream> page = new RestAction<>(streamers).completeGet();
        List<TwitchStream> twitchStreams = page.getItems();
        cursor = page.getCursor();

        return !twitchStreams.isEmpty() ? twitchStreams : null;
    }
}
