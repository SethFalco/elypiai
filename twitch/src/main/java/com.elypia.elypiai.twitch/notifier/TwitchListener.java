package com.elypia.elypiai.twitch.notifier;

import com.elypia.elypiai.twitch.notifier.event.*;

public interface TwitchListener {

    void onFollowEvent(FollowEvent event);
    void onStreamChangedEvent(StreamUpdateEvent event);
}
