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
public enum MapLanguage {

    UNKNOWN(-1),

    @SerializedName("0")
    ANY(0),

    @SerializedName("1")
    OTHER(1),

    @SerializedName("2")
    ENGLISH(2),

    @SerializedName("3")
    JAPANESE(3),

    @SerializedName("4")
    CHINESE(4),

    @SerializedName("5")
    INSTRUMENTAL(5),

    @SerializedName("6")
    KOREAN(6),

    @SerializedName("7")
    FRENCH(7),

    @SerializedName("8")
    GERMAN(8),

    @SerializedName("9")
    SWEDISH(9),

    @SerializedName("10")
    SPANISH(10),

    @SerializedName("11")
    ITALIAN(11);

    private final int ID;

    MapLanguage(int id) {
        ID = id;
    }

    public int getId() {
        return ID;
    }
}
