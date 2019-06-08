package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.MagicType;
import com.google.gson.annotations.SerializedName;

public class MagicCard extends TradingCard {

    @SerializedName("property")
    private MagicType property;

    public MagicType getProperty() {
        return property;
    }
}
