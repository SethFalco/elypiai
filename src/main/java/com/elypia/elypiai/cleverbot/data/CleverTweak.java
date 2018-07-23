package com.elypia.elypiai.cleverbot.data;

public enum CleverTweak {

    /**
     * 0 is more sensible.
     * 100 is more wacky.
     */

    WACKY(1),

    /**
     * 0 is more shy.
     * 100 is more talkative.
     */

    TALKATIVE(2),

    /**
     * 0 is more self-centred.
     * 100 is more attentive.
     */

    ATTENTIVE(3);

    private static final String TWEAK = "cb_settings_tweak";

    private final int ID;

    CleverTweak(int id) {
        ID = id;
    }

    public int getId() {
        return ID;
    }

    public String getSettingName() {
        return TWEAK + ID;
    }
}
