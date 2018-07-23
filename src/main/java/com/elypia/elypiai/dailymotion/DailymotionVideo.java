package com.elypia.elypiai.dailymotion;

import com.google.gson.annotations.SerializedName;

public class DailymotionVideo {

	public static final String DOWNLOAD_ENDPOINT = "http://www.dailymotion.com/json/video/:id";
	private static final String DOWNLOAD_FIELDS = "title,stream_h264_url,stream_h264_ld_url,stream_h264_hq_url,stream_h264_hd_url,stream_h264_hd1080_url";

	private Dailymotion dailymotion;

	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("channel")
	private String channel;

	@SerializedName("owner")
	private String ownerId;

	public Dailymotion getDailymotion() {
		return dailymotion;
	}

	public void setDailymotion(Dailymotion dailymotion) {
		this.dailymotion = dailymotion;
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

// ! Come back to this later
//	/**
//	 * Return the highest quality download link for the video
//	 * id provided, may also provide url. <br>
//	 * Do note: This makes a seperate request.
//	 *
//	 * @param success What to do with the url once complete.
//	 * @param failure What to do in case of failure, eg timeout.
//	 */
//
//	public RestAction<T> getDownloadUrl() {
//		Request req = new Request(DOWNLOAD_ENDPOINT);
//		req.addParam("fields", DOWNLOAD_FIELDS);
//
//		req.get(result -> {
//			JSONObject object = result.asJSONObject();
//
//			if (object.get("stream_h264_hd1080_url") != JSONObject.NULL)
//				success.accept(object.getString("stream_h264_hd1080_url"));
//
//			else if (object.get("stream_h264_hd_url") != JSONObject.NULL)
//				success.accept(object.getString("stream_h264_hd_url"));
//
//			else if (object.get("stream_h264_hq_url") != JSONObject.NULL)
//				success.accept(object.getString("stream_h264_hq_url"));
//
//			else if (object.get("stream_h264_ld_url") != JSONObject.NULL)
//				success.accept(object.getString("stream_h264_ld_url"));
//
//			else if (object.get("stream_h264_url") != JSONObject.NULL)
//				success.accept(object.getString("stream_h264_url"));
//
//			else
//				success.accept(null);
//		}, err -> {
//			failure.accept(err);
//		});
//	}
}
