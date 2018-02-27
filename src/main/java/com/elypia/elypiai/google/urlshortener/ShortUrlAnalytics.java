package com.elypia.elypiai.google.urlshortener;

import org.json.JSONObject;

public class ShortUrlAnalytics {

	private String shortUrl;
	private String longUrl;
	private String status;
	private String created;
	private int shortClicksAllTime;
	private int longClicksAllTime;
	private int shortClicksLastMonth;
	private int longClicksLastMonth;
	private int shortClicksLastWeek;
	private int longClicksLastWeek;
	private int shortClicksLastDay;
	private int longClicksLastDay;
	private int shortClicksLastTwoHours;
	private int longClicksLastTwoHours;

	ShortUrlAnalytics(JSONObject object) {
		shortUrl = object.optString("id", null);
		longUrl = object.getString("longUrl");
		status = object.getString("status");
		created = object.getString("created");

		object = object.getJSONObject("analytics");

		shortClicksAllTime = object.getJSONObject("allTime").optInt("shortUrlClicks");
		longClicksAllTime = object.getJSONObject("allTime").optInt("longUrlClicks");

		shortClicksLastMonth = object.getJSONObject("month").optInt("shortUrlClicks");
		longClicksLastMonth = object.getJSONObject("month").optInt("longUrlClicks");

		shortClicksLastWeek = object.getJSONObject("week").optInt("shortUrlClicks");
		longClicksLastWeek = object.getJSONObject("week").optInt("longUrlClicks");

		shortClicksLastDay = object.getJSONObject("day").optInt("shortUrlClicks");
		longClicksLastDay = object.getJSONObject("day").optInt("longUrlClicks");

		shortClicksLastTwoHours = object.getJSONObject("twoHours").optInt("shortUrlClicks");
		longClicksLastTwoHours = object.getJSONObject("twoHours").optInt("longUrlClicks");
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public String getStatus() {
		return status;
	}

	public String getCreated() {
		return created;
	}

	public int getShortClicksAllTime() {
		return shortClicksAllTime;
	}

	public int getLongClicksAllTime() {
		return longClicksAllTime;
	}

	public int getShortClicksLastMonth() {
		return shortClicksLastMonth;
	}

	public int getLongClicksLastMonth() {
		return longClicksLastMonth;
	}

	public int getShortClicksLastWeek() {
		return shortClicksLastWeek;
	}

	public int getLongClicksLastWeek() {
		return longClicksLastWeek;
	}

	public int getShortClicksLastDay() {
		return shortClicksLastDay;
	}

	public int getLongClicksLastDay() {
		return longClicksLastDay;
	}

	public int getShortClicksLastTwoHours() {
		return shortClicksLastTwoHours;
	}

	public int getLongClicksLastTwoHours() {
		return longClicksLastTwoHours;
	}
}
