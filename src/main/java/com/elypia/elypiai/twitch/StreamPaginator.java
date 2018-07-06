package com.elypia.elypiai.twitch;

import com.elypia.elypiai.utils.okhttp.RestAction;
import com.elypia.elypiai.utils.okhttp.impl.IRestPaginator;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

public class StreamPaginator implements IRestPaginator {

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
        Call<List<TwitchStream>> streamers = twitch.getService().getStreams(query.getUserIds(), query.getUsernames(), query.getGameIds(), limit, cursor);
        List<TwitchStream> streams = new RestAction<>(streamers).complete();

        if (streams.isEmpty())
            return null;

        cursor = streams.get(0).getCursor();
        return streams;
    }
}
