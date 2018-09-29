package com.elypia.elypiai.yugioh.deserializers;

import com.elypia.elypiai.yugioh.*;
import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TradingCardDeserializer implements JsonDeserializer<TradingCard> {

    @Override
    public TradingCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        if (object.get("status").getAsString().equals("fail"))
            return null;

        JsonObject data = object.getAsJsonObject("data");
        CardType type = context.deserialize(data.get("card_type"), CardType.class);

        switch (type) {
            case MONSTER: return context.deserialize(data, MonsterCard.class);
            case SPELL:   return context.deserialize(data, SpellCard.class);
            case TRAP:    return context.deserialize(data, TrapCard.class);
        }

        return null;
    }
}
