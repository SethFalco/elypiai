package com.elypia.elypiai.google.youtube;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
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
	 * @param	apikey 		The apikey obtained from Google Console.
	 * @return				YouTube object.
	 */

	public YouTube(String apiKey) {
		API_KEY = apiKey;
	}

	public void getVideo(String term, Consumer<YouTubeData> success, Consumer<UnirestException> failure) {
		getData(term, YouTubeType.VIDEO, success, failure);
	}

	public void getPlaylist(String term, Consumer<YouTubeData> success, Consumer<UnirestException> failure) {
		getData(term, YouTubeType.PLAYLIST, success, failure);
	}

	public void getChannel(String term, Consumer<YouTubeData> success, Consumer<UnirestException> failure) {
		getData(term, YouTubeType.CHANNEL, success, failure);
	}

	/**
	 * Search YouTube for a video and returns the top
	 * result only with all information.
	 *
	 * @throws RestException
	 *
	 * @param	term	Term to search on YouTube.
	 * @return			Media object with all video information.
	 */

	private void getData(String term, YouTubeType type, Consumer<YouTubeData> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("key", API_KEY);
		queryParams.put("part", "snippet");
		queryParams.put("maxResult", 1);
		queryParams.put("prettyPrint", false);
		queryParams.put("q", term);
		queryParams.put("type", type.toString());

		Unirest.get(MEDIA_ENDPOINT).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();

				if (object.getJSONObject("pageInfo").getInt("totalResults") == 0)
					success.accept(null);
				else
					success.accept(new YouTubeData(object, type));
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