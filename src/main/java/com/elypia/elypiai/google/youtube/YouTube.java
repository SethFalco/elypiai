package com.elypia.elypiai.google.youtube;

import com.elypia.elypiai.utils.okhttp.ElyRequest;
import org.json.JSONObject;

import java.io.IOException;
import java.util.function.Consumer;

public class YouTube {

	public static final String VIDEO_URL = "https://www.youtube.com/watch?v=";
	public static final String PLAYLIST_URL = "https://www.youtube.com/playlist?list=";
	public static final String CHANNEL_URL = "https://www.youtube.com/channel/";
	public static final String THUMBNAIL_URL = "http://img.youtube.com/vi/<ID>/hqdefault.jpg";

	private static final String MEDIA_ENDPOINT = "https://www.googleapis.com/youtube/v3/search";

	private final String API_KEY;

	/**
	 * Create instance of YouTube object to make api calls
	 * in order to recieve YouTube data such as searching
	 * videos or playlists or grabbing all videos under
	 * a playlist.
	 *
	 * @param apiKey The apikey obtained from Google Console.
	 */

	public YouTube(String apiKey) {
		API_KEY = apiKey;
	}

	public void getVideo(String term, Consumer<YouTubeData> success, Consumer<IOException> failure) {
		getData(term, YouTubeType.VIDEO, success, failure);
	}

	public void getPlaylist(String term, Consumer<YouTubeData> success, Consumer<IOException> failure) {
		getData(term, YouTubeType.PLAYLIST, success, failure);
	}

	public void getChannel(String term, Consumer<YouTubeData> success, Consumer<IOException> failure) {
		getData(term, YouTubeType.CHANNEL, success, failure);
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

	public void getData(String term, YouTubeType type, Consumer<YouTubeData> success, Consumer<IOException> failure) {
		ElyRequest req = new ElyRequest(MEDIA_ENDPOINT);
		req.addParam("key", API_KEY);
		req.addParam("part", "snippet");
		req.addParam("maxResult", 1);
		req.addParam("prettyPrint", false);
		req.addParam("q", term);
		req.addParam("type", type.toString());

		req.get(result -> {
			JSONObject object = result.asJSONObject();

			if (object.getJSONObject("pageInfo").getInt("totalResults") == 0)
				success.accept(null);
			else
				success.accept(new YouTubeData(object, type));
		}, err -> {
			failure.accept(err);
		});
	}

	/**
	 * Forms the YouTube url from an identifier provided. <br>
	 * Do note: Does not use an API call.
	 * Simple prepends standard youtube video url.
	 *
	 * @param	id	The identifier of the video.
	 * @return		Url to the video id provided.
	 */

	public static String formVideoUrl(String id) {
		return VIDEO_URL + id;
	}

	/**
	 * Forms the YouTube thumbnail url from an identifier provided. <br>
	 * Do note: Does not use an API call.
	 * Simple inserts id into standard thumbnail url.
	 *
	 * @param	id	The identifier of the video.
	 * @return		Url to the high quality youtube thumbnail.
	 */

	public static String formThumbnailUrl(String id) {
		return THUMBNAIL_URL.replace("<ID>", id);
	}
}
