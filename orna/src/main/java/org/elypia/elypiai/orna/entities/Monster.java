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

package org.elypia.elypiai.orna.entities;

import com.google.gson.annotations.SerializedName;
import org.elypia.elypiai.orna.Orna;
import org.elypia.elypiai.orna.data.*;

import java.util.Collection;

/**
 * Refers to any monster in Orna regardless of status
 * and where or how it's encountered.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public class Monster extends TieredEntity {

    @SerializedName("boss")
    private boolean isBoss;

    /** The image URL that's after. */
    @SerializedName("image")
    private String imageUrl;

    private Collection<Spawn> spawn;

    @SerializedName("weak_to")
    private Collection<Element> weakTo;

    @SerializedName("resistent_to")
    private Collection<Element> resistentTo;

    @SerializedName("immune_to")
    private Collection<Element> immuneTo;

    /** The items this {@link Monster} is able to drop. */
    private Collection<AbstractEntity> drops;

    /** The skills this {@link Monster} is able to perform. */
    private Collection<AbstractEntity> skills;

    /** The quests this {@link Monster} may be involved in. */
    private Collection<AbstractEntity> quests;

    private Collection<AbstractEntity> buffs;

    public int getTier() {
        return tier;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getFullImageUrl() {
        return Orna.getStaticResource(imageUrl);
    }

    public Collection<Spawn> getSpawn() {
        return spawn;
    }

    public Collection<Element> getWeakTo() {
        return weakTo;
    }

    public Collection<Element> getResistentTo() {
        return resistentTo;
    }

    public Collection<Element> getImmuneTo() {
        return immuneTo;
    }
}
