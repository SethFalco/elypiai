package com.elypia.elypiai.dailymotion;

import com.elypia.elypiai.utils.okhttp.Request;

import java.io.IOException;
import java.util.function.Consumer;

public class Dailymotion {

	public static final String GET_VIDEO = "https://api.dailymotion.com/video/%s";
	public static final String PLAYLIST_VIDEOS = "https://api.dailymotion.com/playlist/{id}/videos";

	public void getVideo(String videoId, Consumer<DailymotionVideo> success, Consumer<IOException> failure) {
		Request req = new Request(GET_VIDEO, videoId);

		req.get(result -> {
			success.accept(new DailymotionVideo(result.asJSONObject()));
		}, err -> {
			failure.accept(err);
		});
	}
}
