package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.elypiai.twitch.notifier.*;
import com.elypia.elypiai.twitch.notifier.event.UserUpdatedEvent;
import com.elypia.webhooker.*;

public class UserDispatcher implements Dispatcher {

    private final TwitchNotifier notifier;

    public UserDispatcher(TwitchNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean dispatch(Payload payload) {
        UserUpdatedEvent event = payload.getRequestBody(UserUpdatedEvent.class);

        for (TwitchListener listener : notifier.getListeners())
            listener.onUserUpdateEvent(event);

        return true;
    }
}
