package com.elypia.elypiai.yugioh.impl;

import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.deserializers.TradingCardDeserializer;
import com.google.gson.annotations.*;

@JsonAdapter(TradingCardDeserializer.class)
public abstract class TradingCard {

    @SerializedName("name")
    protected String name;

    @SerializedName("text")
    protected String text;

    @SerializedName("card_type")
    protected CardType type;

    /**
     * @return	The name of the monster.
     */
    public String getName() {
        return name;
    }

    /**
     * @return	The text on the card with the effect
     * 			if the monster has one.
     */
    public String getText() {
        return text;
    }

    /**
     * @return	Return what kind of this card this is,
     * 			can be a Spell, Trap, or Monster.
     */
    public CardType getType() {
        return type;
    }
}
