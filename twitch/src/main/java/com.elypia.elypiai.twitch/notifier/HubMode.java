package com.elypia.elypiai.twitch.notifier;

public enum HubMode {

    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe");

    private final String name;

    HubMode(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
