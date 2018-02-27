package com.elypia.elypiai.dailymotion;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class DailymotionVideo {

	public static final String DOWNLOAD_ENDPOINT = "http://www.dailymotion.com/json/video/:id";
	private static final String DOWNLOAD_FIELDS = "title,stream_h264_url,stream_h264_ld_url,stream_h264_hq_url,stream_h264_hd_url,stream_h264_hd1080_url";

	private String id;
	private String title;
	private String channel;
	private String ownerId;

	public DailymotionVideo(JSONObject object) {
		id = object.getString("id");
		title = object.getString("title");
		channel = object.getString("channel");
		ownerId = object.getString("owner");
	}

	/**
	 * @return	The ID of the video.
	 */

	public String getId() {
		return id;
	}

	/**
	 * @return	The title of the video.
	 */

	public String getTitle() {
		return title;
	}

	public String getChannel() {
		return channel;
	}

	/**
	 * @return	The id of the owner of the video.
	 */

	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Return the highest quality download link for the video
	 * id provided, may also provide url. <br>
	 * Do note: This makes a seperate request.
	 *
	 * @param videoId
	 * @return
	 * @throws RestException
	 */

	public void getDownloadUrl(Consumer<String> success, Consumer<UnirestException> failure) {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("fields", DOWNLOAD_FIELDS);

		Unirest.get(DOWNLOAD_ENDPOINT).routeParam(":id", id).queryString(queryParams).asJsonAsync(new Callback<JsonNode>() {

			@Override
			public void completed(HttpResponse<JsonNode> response) {
				JSONObject object = response.getBody().getObject();

				if (object.get("stream_h264_hd1080_url") != JSONObject.NULL)
					success.accept(object.getString("stream_h264_hd1080_url"));

				else if (object.get("stream_h264_hd_url") != JSONObject.NULL)
					success.accept(object.getString("stream_h264_hd_url"));

				else if (object.get("stream_h264_hq_url") != JSONObject.NULL)
					success.accept(object.getString("stream_h264_hq_url"));

				else if (object.get("stream_h264_ld_url") != JSONObject.NULL)
					success.accept(object.getString("stream_h264_ld_url"));

				else if (object.get("stream_h264_url") != JSONObject.NULL)
					success.accept(object.getString("stream_h264_url"));

				else
					success.accept(null);
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
