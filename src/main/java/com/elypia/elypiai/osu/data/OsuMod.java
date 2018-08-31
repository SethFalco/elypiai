package com.elypia.elypiai.osu.data;

import java.util.*;

public enum OsuMod {

    // NONE(0) | Not including, as NONE should be represented with an empty collection.
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
    AUTOPILOT(8192), // Relex2
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

    private final long BITWISE;
    private final OsuMod[] PARENTS;

    OsuMod(long bitwise, OsuMod... parents) {
        BITWISE = bitwise;
        PARENTS = parents;
    }

    public long getBitwiseValue() {
        return BITWISE;
    }

    public static List<OsuMod> getMods(long bitwise) {
        List<OsuMod> mods = new ArrayList<>();

        for (OsuMod mod : values()) {
            if ((mod.BITWISE & bitwise) == mod.BITWISE)
                mods.add(mod);

            for (OsuMod m : mod.PARENTS)
                mods.remove(m);
        }

        return mods;
    }
}
