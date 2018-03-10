package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.MapGenre;
import com.elypia.elypiai.osu.data.MapLanguage;
import com.elypia.elypiai.osu.data.MapStatus;
import com.elypia.elypiai.osu.data.OsuMode;
import org.json.JSONObject;

import java.util.function.Consumer;

public class BeatMap {

	private MapStatus status;
	private MapGenre genre;
	private MapLanguage language;
	private OsuMode mode;
	private String[] tags;
	private String approved_date;
	private String last_update;
	private String artist;
	private String beatmap_id;
	private String beatmapset_id;
	private int bpm;
	private String creator;
	private double difficultyrating;
	private double diff_size;
	private double diff_overall;
	private double diff_approach;
	private double diff_drain;
	private int hit_length;
	private String source;
	private String title;
	private int total_length;
	private String version;
	private String file_md5;
	private int favourite_count;
	private int playcount;
	private int passcount;
	private int max_combo;

	/**
	 * See {@link Osu#getBeatMap(String, Consumer, Consumer)}
	 */

	public BeatMap(JSONObject object) {
		genre 				= MapGenre.getById(object.optInt("genre_id"));
		status 		 		= MapStatus.getById(object.optInt("approved"));
		language 			= MapLanguage.getById(object.optInt("language_id"));
		mode             	= OsuMode.getById(object.optInt("mode"));
		difficultyrating 	= object.optDouble("difficultyrating");
		bpm 			 	= object.optInt("bpm");
		favourite_count  	= object.optInt("favourite_count");
		playcount		 	= object.optInt("playcount");
		passcount		 	= object.optInt("passcount");
		max_combo		 	= object.optInt("max_combo");
		diff_size 		 	= object.optDouble("diff_size");
		diff_overall 	 	= object.optDouble("diff_overall");
		diff_approach	 	= object.optDouble("diff_approach");
		diff_drain 		 	= object.optDouble("diff_drain");
		hit_length		 	= object.optInt("hit_length");
		total_length	 	= object.optInt("total_length");
		tags			 	= object.getString("tags").split(" ");
		approved_date    	= object.getString("approved_date");
		last_update 	 	= object.getString("last_update");
		artist 			 	= object.getString("artist");
		beatmap_id 		 	= object.getString("beatmap_id");
		beatmapset_id 	 	= object.getString("beatmapset_id");
		creator 			= object.getString("creator");
		source 				= object.getString("source");
		title				= object.getString("title");
		version			 	= object.getString("version");
		file_md5		 	= object.getString("file_md5");
	}

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

	public String getApprovedData() {
		return approved_date;
	}

	/**
	 * Date and time the map was last updated in the format <br>
	 * "YYYY-MM-DD HH:MM:SS" (UTC+8)
	 *
	 * @return 	Date the map was last updated.
	 */

	public String getLastUpdate() {
		return last_update;
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

	public String getBeatMapID() {
		return beatmap_id;
	}

	/**
	 * @return 	The id of the beatmap set. (All difficulties)
	 */

	public String getBeatMapSetID() {
		return beatmapset_id;
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

	/**
	 * @return 	A numerical representation of the beatmap
	 * 			difficulty, unrounded.
	 */

	public double getDiffRating() {
		return difficultyrating;
	}

	/**
	 * @return 	The circle size (CS) for the beatmap.
	 */

	public double getCircleSize() {
		return diff_size;
	}

	/**
	 * @return 	The overall difficulty (OD) of the beatmap.
	 */

	public double getDiffOverall() {
		return diff_overall;
	}

	/**
	 * @return 	The appraoch rate (AR) of the beatmap.
	 */

	public double getApproachRate() {
		return diff_approach;
	}

	/**
	 * @return 	The health drain (HR) of the beatmap.
	 */

	public double getHealthDrain() {
		return diff_drain;
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
		return total_length;
	}

	/**
	 * @return 	The current difficulty's name.
	 */

	public String getVersion() {
		return version;
	}

	public String getFileMD5() {
		return file_md5;
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

	public String[] getTags() {
		return tags;
	}

	/**
	 * @return	Number of times this beatmap was favourtied.
	 */

	public int getFavCount() {
		return favourite_count;
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
		return max_combo;
	}
}
