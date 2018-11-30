package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.deserializers.PageDeserializer;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

@JsonAdapter(PageDeserializer.class)
public class StreamPage {

    private List<Stream> streamers;
    private String cursor;

    public StreamPage(List<Stream> streamers, String cursor) {
        this.streamers = streamers;
        this.cursor = cursor;
    }

    public List<Stream> getStreamers() {
        return streamers;
    }

    public String getCursor() {
        return cursor;
    }
}
