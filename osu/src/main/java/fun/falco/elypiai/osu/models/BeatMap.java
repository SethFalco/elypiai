/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
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

package fun.falco.elypiai.osu.models;

import java.time.OffsetDateTime;

import org.elypia.retropia.gson.deserializers.EmptyNullDeserializer;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class BeatMap {

    /** URL format for the previewable MP3 of an osu! beatmap. */
    private static final String PREVIEW_FORMAT = "https://b.ppy.sh/preview/%d.mp3";

    @SerializedName("approved")
    private MapStatus status;

    @SerializedName("submit_date")
    private OffsetDateTime submitDate;

    @SerializedName("approved_date")
    private OffsetDateTime approvedDate;

    @SerializedName("last_update")
    private OffsetDateTime lastUpdated;

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

    @SerializedName("creator_id")
    private int creatorId;

    @SerializedName("hit_length")
    private int hitLength;

    @SerializedName("source")
    @JsonAdapter(EmptyNullDeserializer.class)
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
    private String tags;

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
     * @return Maps current status.
     */
    public MapStatus getStatus() {
        return status;
    }

    public OffsetDateTime getSubmissionDate() {
        return submitDate;
    }

    /**
     * Date and time the map was approved.
     *
     * @return Date the map was approved.
     */
    public OffsetDateTime getApprovedDate() {
        return approvedDate;
    }

    /**
     * Date and time the map was last updated.
     *
     * @return Date the map was last updated.
     */

    public OffsetDateTime getLastUpdate() {
        return lastUpdated;
    }

    /**
     * @return Artist of the beatmap.
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @return Beatmap ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return ID of the beatmap set. (All difficulties)
     */
    public int getSetId() {
        return setId;
    }

    /**
     * @return BPM of the beatmap.
     */
    public int getBPM() {
        return bpm;
    }

    /**
     * @return Name of the creator of the beatmap.
     */
    public String getCreator() {
        return creator;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public MapDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(MapDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return
     *     Duration of the beatmap from the first note to the last, excluding
     *     breaks in between in seconds.
     */
    public int getHitLength() {
        return hitLength;
    }

    public String getSource() {
        return source;
    }

    /**
     * @return Genre for the song.
     */
    public MapGenre getGenre() {
        return genre;
    }

    public MapLanguage getLanguage() {
        return language;
    }

    /**
     * @return Name of the beatmap.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Total length of the beatmap, inclusive of breaks in seconds.
     */
    public int getTotalLength() {
        return totalLength;
    }

    /**
     * @return Current difficulty's name.
     */
    public String getVersion() {
        return version;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    /**
     * @return What game mode this beatmap is for.
     */
    public OsuMode getMode() {
        return mode;
    }

    /**
     * Tags are space delimited, which may cause confusion when the tags
     * themselves contain spaces.
     *
     * @return Tags associated with this beatmap.
     */
    public String getTags() {
        return tags;
    }

    /**
     * @return Number of times this beatmap was favorited.
     */
    public int getFavoriteCount() {
        return favouriteCount;
    }

    /**
     * @return    Number of times this beatmap was played.
     */
    public int getPlayCount() {
        return playcount;
    }

    /**
     * @return
     *     Number of times the beatmap was completed. (Didn't fail or retry,
     *     finished the map.)
     */
    public int getPassCount() {
        return passcount;
    }

    /**
     * @return Maximum combo possible on this beatmap.
     */
    public int getMaxCombo() {
        return maxCombo;
    }

    /**
     * @return Audio file preview as displayed on the website.
     */
    public String getPreviewUrl() {
        return String.format(PREVIEW_FORMAT, setId);
    }
}
