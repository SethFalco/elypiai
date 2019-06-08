package com.elypia.elypiai.twitch.notifier.event;

import com.elypia.elypiai.twitch.entity.User;

public class UserUpdatedEvent {

    private User user;

    public UserUpdatedEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
