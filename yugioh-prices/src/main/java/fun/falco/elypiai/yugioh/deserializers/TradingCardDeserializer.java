/*
 * Copyright 2019-2025 Seth Falco and Elypiai Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fun.falco.elypiai.yugioh.deserializers;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fun.falco.elypiai.yugioh.MagicCard;
import fun.falco.elypiai.yugioh.Monster;
import fun.falco.elypiai.yugioh.TradingCard;
import fun.falco.elypiai.yugioh.data.CardType;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class TradingCardDeserializer implements JsonDeserializer<TradingCard> {

    private static final Logger logger = LoggerFactory.getLogger(TradingCardDeserializer.class);
    private static final String ON_FAILURE = "The request to the Yu-Gi-Oh! Prices API failed with reason: {}";

    /**
     * The pattern used to split the cards types apart
     * in order to store them as a {@link List}.
     */
    private static final Pattern SPLITTER = Pattern.compile("\\s*/\\s*");

    @Override
    public TradingCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonObject object = json.getAsJsonObject();

        if (object.get("status").getAsString().equals("fail")) {
            logger.warn(ON_FAILURE, object.get("message").getAsString());
            return null;
        }

        JsonObject data = object.getAsJsonObject("data");
        CardType type = context.deserialize(data.get("card_type"), CardType.class);

        switch (type) {
            case MONSTER:
                String typeLine = data.get("type").getAsString();
                String[] split = SPLITTER.split(typeLine);
                Iterator<String> itTypes = List.of(split).iterator();

                data.addProperty("race", itTypes.next());

                JsonArray array = new JsonArray();

                while (itTypes.hasNext()) {
                    array.add(itTypes.next());
                }

                data.add("types", array);

                return context.deserialize(data, Monster.class);
            case SPELL:
            case TRAP:
                return context.deserialize(data, MagicCard.class);
            default:
                return null;
        }
    }
}
