package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.deserializers.PageDeserializer;
import com.google.gson.annotations.JsonAdapter;

import java.util.List;

@JsonAdapter(PageDeserializer.class)
public class TwitchPage<I> {

    private List<I> items;
    private String cursor;

    public TwitchPage(List<I> items, String cursor) {
        this.items = items;
        this.cursor = cursor;
    }

    public List<I> getItems() {
        return items;
    }

    public String getCursor() {
        return cursor;
    }
}
