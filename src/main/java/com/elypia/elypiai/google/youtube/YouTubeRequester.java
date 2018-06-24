package com.elypia.elypiai.google.youtube;

import com.elypia.elypiai.google.youtube.data.YouTubeEndpoint;
import com.elypia.elypiai.google.youtube.data.YouTubeType;
import com.elypia.elypiai.utils.okhttp.Request;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class YouTubeRequester {

    private final YouTube youtube;

    public YouTubeRequester(YouTube youtube) {
        this.youtube = youtube;
    }

    /**
     * Search YouTube for a video and returns the top
     * result only with all information.
     *
     * @param term Term to search on YouTube.
     * @param type Type of YouTube object.
     * @param success What to do with the result.
     * @param failure What to do in case of failure, eg timeout.
     */

    public void getData(String term, int count, YouTubeType type, Consumer<List<YouTubeItem>> success, Consumer<IOException> failure) {
        String endpoint = YouTubeEndpoint.GET_MEDIA.getEndpoint();

        Request req = new Request(endpoint);
        req.addParam("maxResults", count);
        req.addParam("q", term);
        req.addParam("type", type.toString());
        req.addParam("key", youtube.getApiKey());
        req.addParam("part", "snippet");
        req.addParam("prettyPrint", false);

        req.get(result -> {
            List<YouTubeItem> list = new ArrayList<>();
            JSONObject object = result.asJSONObject();
            JSONArray items = object.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject itemObj = items.getJSONObject(i);
                YouTubeItem item = new YouTubeItem(youtube, itemObj, type);
                list.add(item);
            }

            success.accept(list);
        }, err -> {
            failure.accept(err);
        });
    }
}
