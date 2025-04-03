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

package org.elypia.elypiai.runescape.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum Skill {

    /**
     * Should only ever occur if a new skill is introduced
     * and Elypiai hasn't been updated to include yet.
     */
    UNKNOWN(-1, "Unknown", false),

    @SerializedName("0")
    ATTACK(0, "Attack", false),

    @SerializedName("1")
    DEFENCE(1, "Defence", false),

    @SerializedName("2")
    STRENGTH(2, "Strength", false),

    @SerializedName("3")
    CONSTITUTION(3, "Constitution", false),

    @SerializedName("4")
    RANGE(4, "Range", false),

    @SerializedName("5")
    PRAYER(5, "Prayer", false),

    @SerializedName("6")
    MAGIC(6, "Magic", false),

    @SerializedName("7")
    COOKING(7, "Cooking", false),

    @SerializedName("8")
    WOODCUTTING(8, "Woodcutting", false),

    @SerializedName("9")
    FLETCHING(9, "Fletching", false),

    @SerializedName("10")
    FISHING(10, "Fishing", false),

    @SerializedName("11")
    FIREMAKING(11, "Firemaking", false),

    @SerializedName("12")
    CRAFTING(12, "Crafting", false),

    @SerializedName("13")
    SMITHING(13, "Smithing", false),

    @SerializedName("14")
    MINING(14, "Mining", false),

    @SerializedName("15")
    HERBLORE(15, "Herblore", false),

    @SerializedName("16")
    AGILITY(16, "Agility", false),

    @SerializedName("17")
    THIEVING(17, "Theiving", false),

    @SerializedName("18")
    SLAYER(18, "Slayer", false),

    @SerializedName("19")
    FARMING(19, "Farming", false),

    @SerializedName("20")
    RUNECRAFTING(20, "Runecrafting", false),

    @SerializedName("21")
    HUNTER(21, "Hunter", false),

    @SerializedName("22")
    CONSTRUCTION(22, "Construction", false),

    @SerializedName("23")
    SUMMONING(23, "Summoning", false),

    @SerializedName("24")
    DUNGEONEERING(24, "Dungeoneering", false),

    @SerializedName("25")
    DIVINATION(25, "Divination", false),

    @SerializedName("26")
    INVENTION(26, "Invention", true),

    @SerializedName("27")
    ARCHAEOLOGY(27, "Archaeology", false);

    private final int id;
    private final String name;
    private final boolean isElite;

    Skill(int id, String name, boolean isElite) {
        this.id = id;
        this.name = name;
        this.isElite = isElite;
    }

    public int getId() {
        return id;
    }

    public String getDisplay() {
        return name;
    }

    public boolean isElite() {
        return isElite;
    }
}
