package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.data.Realm;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class CompactLeague {

	@SerializedName("id")
	private String id;

	@SerializedName("realm")
	private Realm realm;

	@SerializedName("url")
	private String url;

	@SerializedName("startAt")
	private Date startAt;

	@SerializedName("endAt")
	private Date endAt;

	@SerializedName("delveEvent")
	private boolean delveEvent;

	public String getId() {
		return id;
	}

	public Realm getRealm() {
		return realm;
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

	public boolean isDelveEvent() {
		return delveEvent;
	}
}
