package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.*;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.annotations.SerializedName;

public class TrapCard extends TradingCard {

    @SerializedName("property")
    private TrapProperty property;

    private TrapCard() {

    }

    public TrapProperty getProperty() {
        return property;
    }
}
