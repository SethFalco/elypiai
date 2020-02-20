/*
 * Copyright 2019-2019 Elypia CIC
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

package org.elypia.elypiai.yugioh;

import com.google.gson.annotations.*;
import org.elypia.elypiai.yugioh.data.CardType;
import org.elypia.elypiai.yugioh.deserializers.TradingCardDeserializer;

/**
 * @author seth@elypia.org (Seth Falco)
 */
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
