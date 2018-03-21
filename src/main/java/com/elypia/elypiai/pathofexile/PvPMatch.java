package com.elypia.elypiai.pathofexile;

import com.elypia.elypiai.pathofexile.data.MatchStyle;
import org.json.JSONObject;

import java.time.Instant;

public class PvPMatch extends PoEObject {

	private String id;
	private Instant startAt;
	private Instant endAt;
	private String url;
	private String description;
	private boolean glickoRatings;
	private boolean pvp;
	private MatchStyle style;
	private Instant registerAt;

	public PvPMatch(PathOfExile poe, JSONObject object) {
		super(poe);

		id = object.getString("id");
		startAt = Instant.parse(object.getString("startAt"));
		endAt = Instant.parse(object.getString("endAt"));
		url = object.getString("url");
		description = object.getString("description");
		glickoRatings = object.getBoolean("glickoRatings");
		pvp = object.getBoolean("pvp");
		style = MatchStyle.getTypeByApiName(object.getString("style"));
		registerAt = Instant.parse(object.getString("registerAt"));
	}

	public String getId() {
		return id;
	}

	public Instant getStartDate() {
		return startAt;
	}

	public Instant getEndDate() {
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

	public Instant getRegisterDate() {
		return registerAt;
	}
}
