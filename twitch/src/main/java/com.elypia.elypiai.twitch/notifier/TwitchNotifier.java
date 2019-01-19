package com.elypia.elypiai.twitch.notifier;

import com.elypia.elypiai.twitch.TwitchReceiver;
import com.elypia.elypiai.twitch.notifier.event.*;

import java.util.*;

public class TwitchNotifier {


    private Collection<TwitchListener> listeners;

    public TwitchNotifier() {
        new TwitchReceiver(this);
        listeners = new ArrayList<>();
    }

    private void addListeners(TwitchListener... list) {
        listeners.addAll(Arrays.asList(list));
    }

    private void removeListeners(TwitchListener... list) {
        listeners.removeAll(Arrays.asList(list));
    }

    public void onFollowEvent(FollowEvent event) {
        for (TwitchListener listener : listeners)
            listener.onFollowEvent(event);
    }

    public void onStreamChangedEvent(StreamUpdateEvent event) {
        for (TwitchListener listener : listeners)
            listener.onStreamChangedEvent(event);
    }
}
