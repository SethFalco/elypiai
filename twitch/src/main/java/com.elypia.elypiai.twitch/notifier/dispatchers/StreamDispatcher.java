package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.elypiai.twitch.notifier.*;
import com.elypia.elypiai.twitch.notifier.event.StreamUpdateEvent;
import com.elypia.webhooker.*;

public class StreamDispatcher implements Dispatcher {

    private final TwitchNotifier notifier;

    public StreamDispatcher(TwitchNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean dispatch(Payload payload) {
        StreamUpdateEvent event = payload.getRequestBody(StreamUpdateEvent.class);

        for (TwitchListener listener : notifier.getListeners())
            listener.onStreamUpdateEvent(event);

        return true;
    }
}
