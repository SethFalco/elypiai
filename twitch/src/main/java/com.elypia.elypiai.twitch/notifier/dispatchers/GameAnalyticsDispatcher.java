package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.elypiai.twitch.notifier.*;
import com.elypia.elypiai.twitch.notifier.event.GameAnalyticsEvent;
import com.elypia.webhooker.*;

public class GameAnalyticsDispatcher implements Dispatcher {

    private final TwitchNotifier notifier;

    public GameAnalyticsDispatcher(TwitchNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean dispatch(Payload payload) {
        GameAnalyticsEvent event = payload.getRequestBody(GameAnalyticsEvent.class);

        for (TwitchListener listener : notifier.getListeners())
            listener.onGameAnalyticsEvent(event);

        return true;
    }
}
