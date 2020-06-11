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

import java.util.List;

public class Specialization extends TieredEntity {

    @SerializedName("female_name")
    private String femaleName;

    @SerializedName("cost")
    private String cost;

    @SerializedName("description")
    private String description;

    @SerializedName("images")
    private List<String> imageUrls;

    public String getFemaleName() {
        return femaleName;
    }

    public String getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }
}
