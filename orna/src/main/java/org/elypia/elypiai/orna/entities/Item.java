/*
 * Copyright 2019-2020 Elypia CIC
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

import java.util.*;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Item extends TieredEntity {

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private ItemType type;

    @SerializedName("boss")
    private boolean isBoss;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("element")
    private Element element;

    @SerializedName("materials")
    private List<Entity> materials;

    @SerializedName("dropped_by")
    private List<Entity> droppedBy;

    @SerializedName("equipped_by")
    private List<Entity> equippedBy;

    public String getFullImageUrl() {
        return Orna.getStaticResource(imageUrl);
    }

    public String getDescription() {
        return description;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isBoss() {
        return isBoss;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Element getElement() {
        return element;
    }

    public List<Entity> getMaterials() {
        return Collections.unmodifiableList(materials);
    }

    public List<Entity> getDroppedBy() {
        return Collections.unmodifiableList(droppedBy);
    }

    public List<Entity> getEquippedBy() {
        return Collections.unmodifiableList(equippedBy);
    }
}
