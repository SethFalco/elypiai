/*
 * Copyright 2019-2020 Elypia CIC and Contributors (https://gitlab.com/Elypia/elypiai/-/graphs/master)
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

package org.elypia.elypiai.orna.entities;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.orna.data.SkillType;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class SkillDetails extends Skill {

    @SerializedName("description")
    private String description;

    @SerializedName("is_magic")
    private boolean isMagic;

    @SerializedName("bought")
    private boolean isBought;

    @SerializedName("mana_cost")
    private int manaCost;

    @SerializedName("causes")
    private List<String> causes;

    @SerializedName("gives")
    private List<String> gives;

    @SerializedName("learned_by")
    private List<Void> learnedBy;

    public String getDescription() {
        return description;
    }

    public boolean isMagic() {
        return isMagic;
    }

    public boolean isBought() {
        return isBought;
    }

    /**
     * Always 0 if the {@link SkillType} is {@link SkillType#PASSIVE}.
     *
     * @return Mana it costs to perform this skill.
     */
    public int getManaCost() {
        return manaCost;
    }

    public List<String> getCauses() {
        return causes;
    }

    public List<String> getGives() {
        return gives;
    }

    public List<Void> getLearnedBy() {
        return learnedBy;
    }
}
