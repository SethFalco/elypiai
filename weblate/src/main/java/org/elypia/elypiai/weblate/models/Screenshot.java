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

package org.elypia.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 4.2.3
 */
public class Screenshot {

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getComponent()
     */
    @SerializedName("component")
    private String component;

    /**
     * @see #getFileUrl()
     */
    @SerializedName("file_url")
    private String fileUrl;

    /**
     * @see #getUnits()
     */
    @SerializedName("units")
    private List<String> units;

    public String getName() {
        return name;
    }

    public String getComponent() {
        return component;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public List<String> getUnits() {
        return units;
    }
}
