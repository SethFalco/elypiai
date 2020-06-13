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

package org.elypia.elypiai.osu.data;

import java.util.*;

/**
 * NONE not included as NONE is represented by an empty collection.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public enum OsuMod {

    NO_FAIL(1),
    EASY(2),
    TOUCH_DEVICE(4),
    HIDDEN(8),
    HARD_ROCK(16),
    SUDDEN_DEATH(32),
    DOUBLE_TIME(64),
    RELAX(128),
    HALF_TIME(256),
    NIGHTCORE(512, OsuMod.DOUBLE_TIME),
    FLASH_LIGHT(1024),
    AUTOPLAY(2048),
    SPUN_OUT(4096),

    /** AKA Relax2 */
    AUTOPILOT(8192),

    PERFECT(16384, OsuMod.SUDDEN_DEATH),
    KEY4(32768),
    KEY5(65536),
    KEY6(131072),
    KEY7(262144),
    KEY8(524288),
    FADE_IN(1048576),
    RANDOM(2097152),
    CINEMA(4194304),
    TARGET(8388608),
    KEY9(16777216),
    KEY_COOP(33554432),
    KEY1(67108864),
    KEY3(134217728),
    KEY2(268435456),
    SCOREV2(536870912),
    LAST_MOD(1073741824);

    private final long bitwise;
    private final OsuMod[] parents;

    OsuMod(long bitwise, OsuMod... parents) {
        this.bitwise = bitwise;
        this.parents = parents;
    }

    public long getBitValue() {
        return bitwise;
    }

    public static List<OsuMod> get(long bitwise) {
        List<OsuMod> mods = new ArrayList<>();

        for (OsuMod mod : values()) {
            if ((mod.bitwise & bitwise) == mod.bitwise)
                mods.add(mod);

            for (OsuMod m : mod.parents)
                mods.remove(m);
        }

        return Collections.unmodifiableList(mods);
    }
}
