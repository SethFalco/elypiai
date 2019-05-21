package com.elypia.elypiai.common.data;

public enum AuthenticationType {

    UNKNOWN(null),
    BEARER("bearer"),
    BASIC("basic"),
    DIGEST("digest"),
    OAUTH1("oauth 1.0"),
    OAUTH2("oauth 2.0"),
    HAWK("hawk"),
    AWS("aws"),
    NTML("ntml");

    private final String API_NAME;

    AuthenticationType(final String API_NAME) {
        this.API_NAME = API_NAME;
    }

    public String getApiName() {
        return API_NAME;
    }

    public static AuthenticationType of(String apiName) {
        AuthenticationType[] types = values();

        for (int i = 1; i < types.length; i++) {
            AuthenticationType scope = types[i];

            if (scope.API_NAME.equals(apiName))
                return scope;
        }

        return UNKNOWN;
    }
}
