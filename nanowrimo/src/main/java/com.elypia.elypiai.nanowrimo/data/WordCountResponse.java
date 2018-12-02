package com.elypia.elypiai.nanowrimo.data;

public enum WordCountResponse {

    ERROR_NO_ACTIVE_EVENT(true, "ERROR no active event"),

    ERROR_HASH_MISMATCH(false, "ERROR hash mismatch"),

    ERROR_INVALID_USER(false, "invalid user name");

    private final boolean AUTHORISED;
    private final String MESSAGE;

    WordCountResponse(boolean authorised, String message) {
        AUTHORISED = authorised;
        MESSAGE = message;
    }

    public boolean isAuthorised() {
        return AUTHORISED;
    }

    public String getMessage() {
        return MESSAGE;
    }

    public static WordCountResponse get(String name) {
        for (WordCountResponse response : values()) {
            if (response.MESSAGE.equals(name))
                return response;
        }

        return null;
    }
}
