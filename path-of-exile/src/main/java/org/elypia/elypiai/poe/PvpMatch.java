/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.poe;

import com.google.gson.annotations.*;
import org.elypia.elypiai.poe.data.*;
import org.elypia.retropia.gson.deserializers.IsoDateTimeTemporalDeserializer;

import java.time.Instant;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class PvpMatch {

	@SerializedName("id")
	private String id;

	@SerializedName("realm")
	private Realm realm;

	@SerializedName("startAt")
	@JsonAdapter(IsoDateTimeTemporalDeserializer.class)
	private Instant startAt;

	@SerializedName("endAt")
	@JsonAdapter(IsoDateTimeTemporalDeserializer.class)
	private Instant endAt;

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
	@JsonAdapter(IsoDateTimeTemporalDeserializer.class)
	private Instant registerAt;

	public String getId() {
		return id;
	}

	public Realm getRealm() {
		return realm;
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
