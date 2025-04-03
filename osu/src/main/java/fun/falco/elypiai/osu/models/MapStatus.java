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

package fun.falco.elypiai.osu.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum MapStatus {

    @SerializedName("-2")
    GRAVEYARD(-2),

    @SerializedName("-1")
    WIP(-1),

    @SerializedName("0")
    PENDING(0),

    @SerializedName("1")
    RANKED(1),

    @SerializedName("2")
    APPROVED(2),

    @SerializedName("3")
    QUALIFIED(3),

    @SerializedName("4")
    LOVED(4);

    private final int ID;

    MapStatus(int id) {
        ID = id;
    }

    public int getId() {
        return ID;
    }
}
