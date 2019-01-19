package com.elypia.elypiai.twitch.data;

import java.util.StringJoiner;

public enum Scope {

    UNKNOWN(null),
    ANALYTICS_READ_EXTENSIONS("analytics:read:extensions"),
    ANALYTICS_READ_GAMES("analytics:read:games"),
    BITS_READ("bits:read"),
    CLIPS_EDIT("clips:edit"),
    USER_EDIT("user:edit"),
    USER_EDIT_BROADCAST("user:edit:broadcast"),
    USER_READ_BROADCAST("user:read:broadcast"),
    USER_READ_EMAIL("user:read:email");

    private final String API_NAME;

    Scope(final String API_NAME) {
        this.API_NAME = API_NAME;
    }

    public String getApiName() {
        return API_NAME;
    }

    public static Scope of(String apiName) {
        Scope[] scopes = values();

        for (int i = 1; i < scopes.length; i++) {
            Scope scope = scopes[i];

            if (scope.API_NAME.equals(apiName))
                return scope;
        }

        return UNKNOWN;
    }

    public static String forQuery(Scope... scopes) {
        if (scopes == null)
            return null;

        StringJoiner joiner = new StringJoiner( " ");

        for (Scope scope : scopes)
            joiner.add(scope.API_NAME);

        return joiner.toString();
    }
}
