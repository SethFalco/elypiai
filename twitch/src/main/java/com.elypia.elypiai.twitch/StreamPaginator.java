package com.elypia.elypiai.twitch;

import com.elypia.elypiai.common.RestAction;
import com.elypia.elypiai.common.impl.RestPaginator;
import com.elypia.elypiai.twitch.entity.Stream;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class StreamPaginator implements RestPaginator<Stream> {

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
    public List<Stream> next() throws IOException {
        Call<StreamPage> streamers = twitch.getService().getStreams(
            query.getUserIds(),
            query.getUsernames(),
            query.getGames(),
            limit,
            cursor
        );

        StreamPage page = new RestAction<>(streamers).completeGet();
        List<Stream> streams = page.getStreamers();
        cursor = page.getCursor();

        return !streams.isEmpty() ? streams : null;
    }
}
