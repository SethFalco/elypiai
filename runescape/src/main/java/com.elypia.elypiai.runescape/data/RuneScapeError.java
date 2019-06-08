package com.elypia.elypiai.runescape.data;

public enum RuneScapeError {

    /** An error that hasn't been accomodated by this API. */
    UNKNOWN("Unknown"),

    /**
     * RuneScape players that have their account privacy settings
     * set to private.
     */
    PROFILE_PRIVATE("PROFILE_PRIVATE"),

    /** No account exists with that username. */
    NO_PROFILE("NO_PROFILE"),

    /**
     * Accounts that are no longer active. Potentially
     * permanently banned accounts.
     */
    NOT_A_MEMBER("NOT_A_MEMBER");

    private final String NAME;

    RuneScapeError(final String NAME) {
        this.NAME = NAME;
    }

    public String getName() {
        return NAME;
    }

    public static RuneScapeError get(String name) {
        for (RuneScapeError error : values()) {
            if (error.NAME.equals(name))
                return error;
        }

        return UNKNOWN;
    }
}
