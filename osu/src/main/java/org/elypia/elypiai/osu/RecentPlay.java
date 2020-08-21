/*
 * Copyright 2019-2020 Elypia CIC and Contributors
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

package org.elypia.elypiai.osu;

import com.google.gson.annotations.*;
import org.elypia.elypiai.osu.data.OsuMod;
import org.elypia.elypiai.osu.deserializers.OsuModDeserializer;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class RecentPlay extends OsuScore {

	@SerializedName("beatmap_id")
	private int beatmapId;

	@SerializedName("enabled_mods")
	@JsonAdapter(OsuModDeserializer.class)
	private List<OsuMod> mods;

	@SerializedName("date")
	private OffsetDateTime date;

	@SerializedName("rank")
	private String rank;

	/**
	 * @return	The beatmap id of the recentplay.
	 */
	public int getBeatMapId() {
		return beatmapId;
	}

	public List<OsuMod> getMods() {
		return mods;
	}

	/**
	 * @return 	Date the map was last updated.
	 */
	public OffsetDateTime getDate() {
		return date;
	}

	public String getRank() {
		return rank;
	}
}
