package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.elypiai.twitch.notifier.*;
import com.elypia.elypiai.twitch.notifier.event.FollowEvent;
import com.elypia.webhooker.*;

public class FollowDispatcher implements Dispatcher {

    private final TwitchNotifier notifier;

    public FollowDispatcher(TwitchNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean dispatch(Payload payload) {
        FollowEvent event = payload.getRequestBody(FollowEvent.class);

        for (TwitchListener listener : notifier.getListeners())
            listener.onFollowEvent(event);

        return true;
    }
}
