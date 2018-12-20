package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.deserializers.*;
import com.google.gson.annotations.*;

import java.util.Date;

public class OsuEvent {

    @SerializedName("display_html")
    @JsonAdapter(OsuEventDisplayDeseralizer.class)
    private String message;

    @SerializedName("beatmap_id")
    private int beatmapId;

    @SerializedName("beatmapset_id")
    private int beatmapSetId;

    @SerializedName("date")
    @JsonAdapter(OsuDateDeserializer.class)
    private Date date;

    @SerializedName("epicfactor")
    private int epicFactor;

    public String getMessage() {
        return message;
    }

    public int getBeatmapId() {
        return beatmapId;
    }

    public int getBeatmapSetId() {
        return beatmapSetId;
    }

    public Date getDate() {
        return date;
    }

    public int getEpicFactor() {
        return epicFactor;
    }
}
