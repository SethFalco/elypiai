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

package org.elypia.elypiai.poe.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class Exile {

    @SerializedName("name")
    private String name;

    @SerializedName("level")
    private int level;

    @SerializedName("class")
    private String ascendancy;

    @SerializedName("id")
    private String id;

    @SerializedName("experience")
    private long experience;

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    /**
     * @return
     *     Class of the {@link Exile}, null if there is no specific
     *     {@link AscendancyClass} but just {@link AscendancyType}, or
     *     {@link AscendancyClass#UNKNOWN} if the class/type is not known to
     *     this API wrapper.
     */
    public AscendancyClass getAscendancyClass() {
        AscendancyClass aclass = AscendancyClass.get(ascendancy);

        if (aclass != AscendancyClass.UNKNOWN) {
            return aclass;
        }

        AscendancyType type = getAscendancy();

        if (type != AscendancyType.UNKNOWN) {
            return null;
        }

        return AscendancyClass.UNKNOWN;
    }

    public AscendancyType getAscendancy() {
        AscendancyType type = AscendancyType.get(ascendancy);
        return (type != AscendancyType.UNKNOWN) ? type : getAscendancyClass().getType();
    }

    public String getId() {
        return id;
    }

    public long getExperience() {
        return experience;
    }
}
