package com.elypia.elypiai.google.youtube;

import com.elypia.elypiai.google.youtube.data.YouTubeType;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class YouTube {

	public static final String VIDEO_URL = "https://www.youtube.com/watch?v=";
	public static final String PLAYLIST_URL = "https://www.youtube.com/playlist?list=";
	public static final String CHANNEL_URL = "https://www.youtube.com/channel/";
	public static final String THUMBNAIL_URL = "http://img.youtube.com/vi/<ID>/hqdefault.jpg";

	private final String API_KEY;

	private YouTubeRequester requester;

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
		requester = new YouTubeRequester(this);
	}

	public void getVideo(String term, Consumer<YouTubeItem> success, Consumer<IOException> failure) {
		getVideos(term, 1, result -> {
			YouTubeItem item = result.isEmpty() ? null : result.get(0);
			success.accept(item);
		}, failure);
	}

	public void getVideos(String term, int count, Consumer<List<YouTubeItem>> success, Consumer<IOException> failure) {
		if (count < 1 || count > 50)
			throw new IllegalArgumentException("YouTube search endpoint can only return between 0 and 50 results.");

		requester.getData(term, count, YouTubeType.VIDEO, success, failure);
	}

	public void getPlaylist(String term, Consumer<YouTubeItem> success, Consumer<IOException> failure) {
		requester.getData(term, 1, YouTubeType.PLAYLIST, result -> {
			YouTubeItem item = result.isEmpty() ? null : result.get(0);
			success.accept(item);
		}, failure);
	}

	public void getChannel(String term, Consumer<YouTubeItem> success, Consumer<IOException> failure) {
		requester.getData(term, 1, YouTubeType.CHANNEL, result -> {
			YouTubeItem item = result.isEmpty() ? null : result.get(0);
			success.accept(item);
		}, failure);
	}

	public String getApiKey() {
		return API_KEY;
	}

	/**
	 * Forms the YouTube url from an identifier provided. <br>
	 * Do note: Does not use an API call.
	 * Simple prepends standard youtube video url.
	 *
	 * @param	id	The identifier of the video.
	 * @return		Url to the video id provided.
	 */

	public static String getVideoUrl(String id) {
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

	public static String getThumbnailUrl(String id) {
		return THUMBNAIL_URL.replace("<ID>", id);
	}
}
