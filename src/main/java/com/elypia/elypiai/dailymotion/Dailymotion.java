package com.elypia.elypiai.dailymotion;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Dailymotion {

	public static final String GET_VIDEO = "https://api.dailymotion.com/video/{id}";
	public static final String PLAYLIST_VIDEOS = "https://api.dailymotion.com/playlist/{id}/videos";

	public void getVideo(String videoId, Consumer<DailymotionVideo> success, Consumer<UnirestException> failure) {
		Unirest.get(GET_VIDEO).routeParam("id", videoId).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				success.accept(new DailymotionVideo(response.getBody().getObject()));
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}

	public void getVideosFromPlaylist(String playlistId, Consumer<List<DailymotionVideo>> success, Consumer<UnirestException> failure) {
		List<DailymotionVideo> videos = new ArrayList<>();

		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("limit", 100);

		int page = 1;
		queryParams.put("page", page++);

		Unirest.get(PLAYLIST_VIDEOS).routeParam("id", playlistId).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();

				JSONArray array = object.getJSONArray("list");

				for (int i = 0; i < array.length(); i++)
					videos.add(new DailymotionVideo(array.getJSONObject(i)));

				success.accept(videos);
			}

			@Override
			public void failed(UnirestException e) {
				failure.accept(e);
			}

			@Override
			public void cancelled() {

			}
		});
	}
}
