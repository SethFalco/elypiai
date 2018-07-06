package com.elypia.elypiai.yugioh.deserializers;

import com.elypia.elypiai.yugioh.*;
import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TradingCardDeserializer implements JsonDeserializer<TradingCard> {

    @Override
    public TradingCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject().getAsJsonObject("data");
        CardType type = context.deserialize(object.get("card_type"), CardType.class);

        switch (type) {
            case MONSTER: return context.deserialize(object, MonsterCard.class);
            case SPELL: return context.deserialize(object, SpellCard.class);
            case TRAP: return context.deserialize(object, TrapCard.class);
        }

        return null;
    }
}
