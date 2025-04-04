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

package fun.falco.elypiai.poe.models;

import java.awt.Color;

import com.google.gson.annotations.SerializedName;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum GemColor {

    UNKNOWN(null, null),

    @SerializedName("R")
    RED("R", Color.RED),

    @SerializedName("B")
    BLUE("B", Color.BLUE),

    @SerializedName("G")
    GREEN("G", Color.GREEN),

    @SerializedName("W")
    WHITE("W", Color.WHITE);

    private final String NAME;
    private final Color COLOR;

    GemColor(String name, Color color) {
        NAME = name;
        COLOR = color;
    }

    public String getName() {
        return NAME;
    }

    public Color getColor() {
        return COLOR;
    }

    public static GemColor get(String name) {
        for (GemColor color : GemColor.values()) {
            if (color.NAME.equals(name)) {
                return color;
            }
        }

        return UNKNOWN;
    }
}
