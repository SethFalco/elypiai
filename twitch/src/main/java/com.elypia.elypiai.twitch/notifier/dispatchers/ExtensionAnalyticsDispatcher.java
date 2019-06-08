package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.elypiai.twitch.notifier.*;
import com.elypia.elypiai.twitch.notifier.event.ExtensionAnalyticsEvent;
import com.elypia.webhooker.*;

public class ExtensionAnalyticsDispatcher implements Dispatcher {

    private final TwitchNotifier notifier;

    public ExtensionAnalyticsDispatcher(TwitchNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public boolean dispatch(Payload payload) {
        ExtensionAnalyticsEvent event = payload.getRequestBody(ExtensionAnalyticsEvent.class);

        for (TwitchListener listener : notifier.getListeners())
            listener.onExtensionAnalytics(event);

        return true;
    }
}
