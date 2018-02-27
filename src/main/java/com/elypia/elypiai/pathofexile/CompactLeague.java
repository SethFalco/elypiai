package com.elypia.elypiai.pathofexile;

import org.json.JSONObject;

import java.time.Instant;

public class CompactLeague extends PoEObject {

	private String id;
	private String url;
	private Instant startAt;
	private Instant endAt;

	public CompactLeague(PathOfExile poe, JSONObject object) {
		super(poe);

		id = object.getString("id");
		url = object.getString("url");

		String startDate = object.optString("startAt", null);

		if (startDate != null)
			startAt = Instant.parse(startDate);

		String endDate = object.optString("endAt", null);

		if (endDate != null)
			endAt = Instant.parse(endDate);
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public Instant getStartAt() {
		return startAt;
	}

	public Instant getEndAt() {
		return endAt;
	}
}
