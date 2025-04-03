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

package org.elypia.elypiai.orna.entities;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.orna.Orna;
import org.elypia.elypiai.orna.data.Element;
import org.elypia.elypiai.orna.data.Spawn;

import java.util.Collections;
import java.util.List;

/**
 * Refers to any monster in Orna regardless of status
 * and where or how it's encountered.
 *
 * @author seth@falco.fun (Seth Falco)
 */
public class Monster extends TieredEntity {

    @SerializedName("boss")
    private boolean isBoss;

    @SerializedName("image")
    private String imageUrl;

    private List<Spawn> spawn;

    @SerializedName("weak_to")
    private List<Element> weakTo;

    @SerializedName("resistent_to")
    private List<Element> resistentTo;

    @SerializedName("immune_to")
    private List<Element> immuneTo;

    private List<Entity> drops;

    private List<Entity> skills;

    private List<Entity> quests;

    private List<Entity> buffs;

    public String getFullImageUrl() {
        return Orna.getStaticResource(imageUrl);
    }

    public boolean isBoss() {
        return isBoss;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Spawn> getSpawn() {
        return Collections.unmodifiableList(spawn);
    }

    public List<Element> getWeakTo() {
        return Collections.unmodifiableList(weakTo);
    }

    public List<Element> getResistentTo() {
        return Collections.unmodifiableList(resistentTo);
    }

    public List<Element> getImmuneTo() {
        return Collections.unmodifiableList(immuneTo);
    }

    /**
     * @return Items this {@link Monster} is able to drop.
     */
    public List<Entity> getDrops() {
        return Collections.unmodifiableList(drops);
    }

    /**
     * @return Skills this {@link Monster} is able to perform.
     */
    public List<Entity> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    /**
     * @return Quests this {@link Monster} may be involved in.
     */
    public List<Entity> getQuests() {
        return Collections.unmodifiableList(quests);
    }

    public List<Entity> getBuffs() {
        return Collections.unmodifiableList(buffs);
    }
}
