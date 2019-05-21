package com.elypia.elypiai.yugioh.deserializers;

import com.elypia.elypiai.yugioh.MagicCard;
import com.elypia.elypiai.yugioh.Monster;
import com.elypia.elypiai.yugioh.data.CardType;
import com.elypia.elypiai.yugioh.impl.TradingCard;
import com.google.gson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class TradingCardDeserializer implements JsonDeserializer<TradingCard> {

    private static final String ON_FAILURE = "The request to the Yu-Gi-Oh! Prices API failed with reason: {}";

    private static final Logger logger = LoggerFactory.getLogger(TradingCardDeserializer.class);

    /**
     * The pattern used to split the cards types apart
     * in order to store them as a {@link List}.
     */
    private static final Pattern SPLITTER = Pattern.compile("\\s*/\\s*");

    @Override
    public TradingCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = json.getAsJsonObject();

        if (object.get("status").getAsString().equals("fail")) {
            logger.warn(ON_FAILURE, object.get("message").getAsString());
            return null;
        }

        JsonObject data = object.getAsJsonObject("data");
        CardType type = context.deserialize(data.get("card_type"), CardType.class);

        switch (type) {
            case MONSTER: {
                String typeLine = data.get("type").getAsString();
                String[] split = SPLITTER.split(typeLine);
                Iterator<String> itTypes = List.of(split).iterator();

                data.addProperty("race", itTypes.next());

                JsonArray array = new JsonArray();

                while (itTypes.hasNext())
                    array.add(itTypes.next());

                data.add("types", array);

                return context.deserialize(data, Monster.class);
            }
            case SPELL:
            case TRAP: {
                return context.deserialize(data, MagicCard.class);
            }
            default: {
                return null;
            }
        }
    }
}
