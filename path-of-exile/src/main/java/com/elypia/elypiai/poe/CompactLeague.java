package com.elypia.elypiai.poe;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CompactLeague {

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("startAt")
	private Date startAt;

	@SerializedName("endAt")
	private Date endAt;

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public Date getStartDate() {
		return startAt;
	}

	public Date getEndDate() {
		return endAt;
	}
}
