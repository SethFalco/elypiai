package com.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

public enum SpellProperty {

    @SerializedName("Normal")
    NORMAL,

    @SerializedName("Continuous")
    CONTINUOUS,

    @SerializedName("Ritual")
    RITUAL,

    @SerializedName("Quick-Play")
    QUICK_PLAY,

    @SerializedName("Field")
    FIELD,

    @SerializedName("Equip")
    EQUIP,
}
