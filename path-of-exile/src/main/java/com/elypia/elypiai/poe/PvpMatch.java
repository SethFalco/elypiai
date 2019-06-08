package com.elypia.elypiai.poe;

import com.elypia.elypiai.poe.data.MatchStyle;
import com.elypia.elypiai.poe.data.Realm;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PvpMatch {

	@SerializedName("id")
	private String id;

	@SerializedName("realm")
	private Realm realm;

	@SerializedName("startAt")
	private Date startAt;

	@SerializedName("endAt")
	private Date endAt;

	@SerializedName("url")
	private String url;

	@SerializedName("description")
	private String description;

	@SerializedName("glickoRatings")
	private boolean glickoRatings;

	@SerializedName("pvp")
	private boolean pvp;

	@SerializedName("style")
	private MatchStyle style;

	@SerializedName("registerAt")
	private Date registerAt;

	public String getId() {
		return id;
	}

	public Realm getRealm() {
		return realm;
	}

	public Date getStartDate() {
		return startAt;
	}

	public Date getEndDate() {
		return endAt;
	}

	public String getUrl() {
		return url;
	}

	public String getDescription() {
		return description;
	}

	public boolean isGlickoRatings() {
		return glickoRatings;
	}

	public boolean isPvp() {
		return pvp;
	}

	public MatchStyle getStyle() {
		return style;
	}

	public Date getRegisterDate() {
		return registerAt;
	}
}
