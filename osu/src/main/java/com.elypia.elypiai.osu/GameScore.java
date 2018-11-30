package com.elypia.elypiai.osu;

import com.elypia.elypiai.osu.data.OsuTeam;
import com.elypia.elypiai.osu.deserializers.BitBooleanDeserializer;
import com.elypia.elypiai.osu.impl.OsuScore;
import com.google.gson.annotations.*;

public class GameScore extends OsuScore {

    @SerializedName("slot")
    private int slot;

    @SerializedName("team")
    private OsuTeam team;

    @SerializedName("pass")
    @JsonAdapter(BitBooleanDeserializer.class)
    private boolean pass;

    public int getSlot() {
        return slot;
    }

    public OsuTeam getTeam() {
        return team;
    }

    public boolean isPass() {
        return pass;
    }
}
