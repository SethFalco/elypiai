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

package org.elypia.elypiai.yugioh.data;

import com.google.gson.annotations.SerializedName;

/**
 * The card race is the first item that appears in the
 * type-line on the monster. <br>
 * The English cards have this labelled as "Type". This
 * class is named after the direct translation from the
 * Japanese, "Shuzoku".
 *
 * @author seth@falco.fun (Seth Falco)
 */
public enum Race {

    UNKNOWN("Unknown"),

    @SerializedName("Dragon")
    DRAGON("Dragon"),

    @SerializedName("Spellcaster")
    SPELLCASTER("Spellcaster"),

    @SerializedName("Zombie")
    ZOMBIE("Zombie"),

    @SerializedName("Warrior")
    WARRIOR("Warrior"),

    @SerializedName("Beast-Warrior")
    BEAST_WARRIOR("Beast-Warrior"),

    @SerializedName("Beast")
    BEAST("Beast"),

    @SerializedName("Winged Beast")
    WINGED_BEAST("Winged Beast"),

    @SerializedName("Fiend")
    FIEND("Fiend"),

    @SerializedName("Fairy")
    FAIRY("Fairy"),

    @SerializedName("Insect")
    INSECT("Insect"),

    @SerializedName("Dinosaur")
    DINOSAUR("Dinosaur"),

    @SerializedName("Reptile")
    REPTILE("Reptile"),

    @SerializedName("Fish")
    FISH("Fish"),

    @SerializedName("Sea Serpent")
    SEA_SERPENT("Sea Serpent"),

    @SerializedName("Machine")
    MACHINE("Machine"),

    @SerializedName("Thunder")
    THUNDER("Thunder"),

    @SerializedName("Aqua")
    AQUA("Aqua"),

    @SerializedName("Pyro")
    PYRO("Pyro"),

    @SerializedName("Rock")
    ROCK("Rock"),

    @SerializedName("Plant")
    PLANT("Plant"),

    @SerializedName("Psychic")
    PSYCHIC("Psychic"),

    @SerializedName("Divine-Beast")
    DIVINE_BEAST("Divine-Beast"),

    @SerializedName("WYRM")
    WYRM("WYRM");

    private final String NAME;

    Race(final String name) {
        NAME = name;
    }

    public String getName() {
        return NAME;
    }

    public static Race get(String name) {
        for (Race attribute : values()) {
            if (attribute.NAME.equals(name)) {
                return attribute;
            }
        }

        return UNKNOWN;
    }
}
