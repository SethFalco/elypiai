package com.elypia.elypiai.yugioh.data;

import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.annotations.SerializedName;

/**
 * This tells us the subtype of magic or spell {@link TradingCard}
 * this is.
 */
public enum MagicType {

    @SerializedName("Normal")
    NORMAL("Normal", true, true),

    @SerializedName("Continuous")
    CONTINUOUS("Continous", true, true),

    @SerializedName("Ritual")
    RITUAL("Ritual", true, false),

    @SerializedName("Quick-Play")
    QUICK_PLAY("Quick-Play", true, false),

    @SerializedName("Field")
    FIELD("Field", true, false),

    @SerializedName("Equip")
    EQUIP("Equip", true, false),

    @SerializedName("Counter")
    COUNTER("Counter", false, true);

    /**
     * The name of this property.
     */
    private final String NAME;

    /**
     * Can a spell card have this property.
     */
    private final boolean SPELL;

    /**
     * Can a trap card have this property.
     */
    private final boolean TRAP;

    MagicType(final String name, final boolean spell, final boolean trap) {
        NAME = name;
        SPELL = spell;
        TRAP = trap;
    }

    public String getName() {
        return NAME;
    }

    public boolean isSpell() {
        return SPELL;
    }

    public boolean isTrap() {
        return TRAP;
    }

    public static MagicType get(String name) {
        for (MagicType property : values()) {
            if (property.NAME.equals(name))
                return property;
        }

        return null;
    }
}
