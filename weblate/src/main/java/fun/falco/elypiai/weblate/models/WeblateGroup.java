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

package fun.falco.elypiai.weblate.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 * @since 4.2.3
 */
public class WeblateGroup {

    /**
     * @see #getName()
     */
    @SerializedName("name")
    private String name;

    /**
     * @see #getProjectSelection()
     */
    @SerializedName("project_selection")
    private int projectSelection;

    /**
     * @see #getLangaugeSelection()
     */
    @SerializedName("language_selection")
    private int langaugeSelection;

    public String getName() {
        return name;
    }

    public int getProjectSelection() {
        return projectSelection;
    }

    public int getLangaugeSelection() {
        return langaugeSelection;
    }
}
