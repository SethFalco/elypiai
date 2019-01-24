package com.elypia.elypiai.twitch.notifier;

import com.elypia.elypiai.twitch.notifier.event.*;
import com.elypia.webhooker.*;
import com.elypia.webhooker.annotation.Mapping;

import java.util.*;

@Mapping("twitch")
public class TwitchNotifier extends Receiver {

    private Collection<TwitchListener> listeners;

    /**
     * This will run a webserver that can accept webhooks.
     * In order to do this you will need to ensure you have
     * a system with with a port open for other systems to
     * make requests too.
     *
     * You will need a domain, or public IP address for Twitch
     * to make requests with, and to ensure whatever port you specify
     * is set to allow from all location in your firewall.
     */
    public TwitchNotifier(String domain) {
        listeners = new ArrayList<>();
    }

    private void addListeners(TwitchListener... list) {
        listeners.addAll(Arrays.asList(list));
    }

    private void removeListeners(TwitchListener... list) {
        listeners.removeAll(Arrays.asList(list));
    }

    @Override
    public boolean beforeAny(Payload payload) {
        Map<String, String> params = payload.getRequest().params();

        if (!params.containsKey("hub.challenge"))
            return true;

        payload.getResponse().body(params.get("hub.challenge"));
        return false;
    }

    @Mapping("user_follow")
    public void onFollowEvent(FollowEvent event) {
        for (TwitchListener listener : listeners)
            listener.onFollowEvent(event);
    }

    @Mapping("stream_update")
    public void onStreamUpdateEvent(StreamUpdateEvent event) {
        for (TwitchListener listener : listeners)
            listener.onStreamUpdateEvent(event);
    }

    @Mapping("user_update")
    public void onUserUpdateEvent(UserUpdatedEvent event) {
        for (TwitchListener listener : listeners)
            listener.onUserUpdateEvent(event);
    }

    @Mapping("game_analytics")
    public void onGameAnalyticsEvent(GameAnalyticsEvent event) {
        for (TwitchListener listener : listeners)
            listener.onGameAnalyticsEvent(event);
    }

    @Mapping("extension_analytics")
    public void onExtensionAnalyticsEvent(ExtensionAnalyticsEvent event) {
        for (TwitchListener listener : listeners)
            listener.onExtensionAnalytics(event);
    }
}
