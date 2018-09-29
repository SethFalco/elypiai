package com.elypia.elypiai.yugioh;

import com.elypia.elypiai.yugioh.data.SpellProperty;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.annotations.SerializedName;

public class SpellCard extends TradingCard {

    @SerializedName("property")
    private SpellProperty property;

    private SpellCard() {

    }

    public SpellProperty getProperty() {
        return property;
    }
}
