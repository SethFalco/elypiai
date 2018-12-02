package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMod;
import com.elypia.elypiai.osu.deserializers.*;
import com.elypia.elypiai.osu.impl.OsuScore;
import com.google.gson.annotations.*;

import java.util.*;

public class RecentPlay extends OsuScore {

	@SerializedName("beatmap_id")
	private int beatmapId;

	@SerializedName("enabled_mods")
	@JsonAdapter(OsuModDeserializer.class)
	private List<OsuMod> mods;

	@SerializedName("date")
	@JsonAdapter(OsuDateDeserializer.class)
	private Date date;

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
	 * Date and time the map was played in the format: <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was last updated.
	 */
	public Date getDate() {
		return date;
	}

	public String getRank() {
		return rank;
	}
}
