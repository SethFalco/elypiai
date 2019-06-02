package com.elypia.elypiai.common.core;

public class FriendlyException extends RuntimeException {

    /** A tag to identify the error. */
    private final String tag;

    /** If the tag associated with it is from the API, or internally managed in Elypiai. */
    private final boolean isInternal;

    /**
     * @param tag The tag associated with this exception.
     * @param message A friendly default error message which can be safe printed.
     */
    public FriendlyException(String tag, String message) {
        this(tag, message, false);
    }

    /**
     * @param tag The tag associated with this exception.
     * @param message A friendly default error message which can be safe printed.
     * @param isInternal Is the tag defined is internally managed or by the API.
     */
    public FriendlyException(String tag, String message, boolean isInternal) {
        super(message);
        this.tag = tag;
        this.isInternal = isInternal;
    }

    public String getTag() {
        return tag;
    }

    public boolean isInternal() {
        return isInternal;
    }
}
