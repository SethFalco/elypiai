package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.*;
import com.elypia.elypiai.utils.gson.deserializers.*;
import com.google.gson.annotations.*;

import java.util.*;

public class BeatMap {

	private static final String PREVIEW_FORMAT = "https://b.ppy.sh/preview/%d.mp3";

	@SerializedName("approved")
	private MapStatus status;

	@SerializedName("approved_date")
	private Date approvedDate;

	@SerializedName("last_update")
	private Date lastUpdated;

	@SerializedName("artist")
	private String artist;

	@SerializedName("beatmap_id")
	private int id;

	@SerializedName("beatmapset_id")
	private int setId;

	@SerializedName("bpm")
	private int bpm;

	@SerializedName("creator")
	private String creator;

	@SerializedName("hit_length")
	private int hit_length;

	@SerializedName("source")
	@JsonAdapter(StringEmptyIsNullDeserializer.class)
	private String source;

	@SerializedName("genre_id")
	private MapGenre genre;

	@SerializedName("language_id")
	private MapLanguage language;

	@SerializedName("title")
	private String title;

	@SerializedName("total_length")
	private int totalLength;

	@SerializedName("version")
	private String version;

	@SerializedName("file_md5")
	private String fileMd5;

	@SerializedName("mode")
	private OsuMode mode;

	@SerializedName("tags")
	@JsonAdapter(SpaceDelimitedStringDeserializer.class)
	private List<String> tags;

	@SerializedName("favourite_count")
	private int favouriteCount;

	@SerializedName("playcount")
	private int playcount;

	@SerializedName("passcount")
	private int passcount;

	@SerializedName("max_combo")
	private int maxCombo;

	private MapDifficulty difficulty;

	/**
	 * @return	The maps current status.
	 */

	public MapStatus getStatus() {
		return status;
	}

	/**
	 * Date and time the map was approved in the format <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was approved.
	 */

	public Date getApprovedDate() {
		return approvedDate;
	}

	/**
	 * Date and time the map was last updated in the format <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was last updated.
	 */

	public Date getLastUpdate() {
		return lastUpdated;
	}

	/**
	 * @return 	The artist of the beatmap.
	 */

	public String getArtist() {
		return artist;
	}

	/**
	 * @return 	The beatmap id.
	 */

	public int getId() {
		return id;
	}

	/**
	 * @return 	The id of the beatmap set. (All difficulties)
	 */

	public int getSetId() {
		return setId;
	}

	/**
	 * @return 	The BPM of the beatmap.
	 */

	public int getBPM() {
		return bpm;
	}

	/**
	 * @return 	The name of the creator of the beatmap.
	 */

	public String getCreator() {
		return creator;
	}

	public MapDifficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(MapDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return 	The duration of the beatmap from the first note
	 * 			to the last, excluding breaks in between in seconds.
	 */

	public int getHitLength() {
		return hit_length;
	}

	public String getSource() {
		return source;
	}

	/**
	 * @return 	The genre for the song.
	 */

	public MapGenre getGenre() {
		return genre;
	}

	public MapLanguage getLanguage() {
		return language;
	}

	/**
	 * @return 	The name of the beatmap.
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * @return 	The total length of the beatmap,
	 * 			inclusive of breaks in seconds.
	 */

	public int getTotalLength() {
		return totalLength;
	}

	/**
	 * @return 	The current difficulty's name.
	 */

	public String getVersion() {
		return version;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	/**
	 * @return 	What game mode this beatmap is for.
	 */

	public OsuMode getMode() {
		return mode;
	}

	/**
	 * @return	Tags associated with this beatmap.
	 */

	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return	Number of times this beatmap was favourtied.
	 */

	public int getFavouriteCount() {
		return favouriteCount;
	}

	/**
	 * @return	Number of times this beatmap was played.
	 */

	public int getPlayCount() {
		return playcount;
	}

	/**
	 * @return	Numvber of times the beatmap was completed.
	 * 			(Didn't fail or retry, finished the map.)
	 */

	public int getPassCount() {
		return passcount;
	}

	/**
	 * @return	The maximum combo possible on this beatmap.
	 */

	public int getMaxCombo() {
		return maxCombo;
	}

	/**
	 * @return The audio file preview as displayed on the website.
	 */

	public String getPreviewUrl() {
		return String.format(PREVIEW_FORMAT, setId);
	}
}
