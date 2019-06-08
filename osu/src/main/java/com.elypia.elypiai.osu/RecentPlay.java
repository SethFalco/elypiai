package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuMod;
import com.elypia.elypiai.osu.deserializers.OsuModDeserializer;
import com.elypia.elypiai.osu.impl.OsuScore;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class RecentPlay extends OsuScore {

	@SerializedName("beatmap_id")
	private int beatmapId;

	@SerializedName("enabled_mods")
	@JsonAdapter(OsuModDeserializer.class)
	private List<OsuMod> mods;

	@SerializedName("date")
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
	 * @return 	Date the map was last updated.
	 */
	public Date getDate() {
		return date;
	}

	public String getRank() {
		return rank;
	}
}
