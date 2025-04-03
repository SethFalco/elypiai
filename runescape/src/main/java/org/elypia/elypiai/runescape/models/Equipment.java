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

package org.elypia.elypiai.runescape.models;

/**
 * @author seth@falco.fun (Seth Falco)
 */
public enum Equipment {

    UNKNOWN(-1),
    HEAD(0),
    CAPE(1),
    NECK(2),
    MAIN_HAND_UNSHEATHED(3),
    BODY(4),
    OFF_HAND_UNSHEATHED(5),
    TWO_HANDED_UNSHEATHED(6),
    LEG_WEAR(7),
    HANDS(9),
    FEET(10),
    RING(12),
    AMMUNITION(13),
    AURA(14),
    MAIN_HANDED_SHEATHED(15),
    OFF_HAND_SHEATHED(16),
    POCKET(17),
    WINGS(18);

    private int id;

    Equipment(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
