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

import java.util.Collections;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Pet extends TieredEntity {

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("cost")
    private int cost;

    @SerializedName("skills")
    private List<Entity> skills;

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getCost() {
        return cost;
    }

    public List<Entity> getSkills() {
        return Collections.unmodifiableList(skills);
    }
}
