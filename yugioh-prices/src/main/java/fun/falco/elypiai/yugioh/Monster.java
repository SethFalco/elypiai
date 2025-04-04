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

package fun.falco.elypiai.yugioh;

import java.util.Collection;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import fun.falco.elypiai.yugioh.data.Attribute;
import fun.falco.elypiai.yugioh.data.MonsterType;
import fun.falco.elypiai.yugioh.data.Race;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public class Monster extends TradingCard {

    @SerializedName("family")
    private Attribute attribute;

    @SerializedName("level")
    private int level;

    @SerializedName("type")
    private String typeLine;

    @SerializedName("atk")
    private int attack;

    @SerializedName("def")
    private int defense;

    /**
     * This is not returned by the API but added to the
     * object pre-deserialization.
     */
    @SerializedName("race")
    private Race race;

    /**
     * This is not returned by the API but added to the
     * object pre-deserialization.
     */
    @SerializedName("types")
    private List<MonsterType> types;

    /**
     * @return Attribute/attribute of the card.
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * @return Level of the monster.
     */
    public int getLevel() {
        return level;
    }

    public String getTypeLine() {
        return typeLine;
    }

    /**
     * @return Attack score of the monster.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * @return Defence score of the monster.
     */
    public int getDefense() {
        return defense;
    }

    public Race getRace() {
        return race;
    }

    /**
     * @return Monsters type.
     */
    public Collection<MonsterType> getTypes() {
        return types;
    }
}
