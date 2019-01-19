package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.notifier.TwitchNotifier;
import com.elypia.elypiai.twitch.notifier.event.*;
import com.elypia.webhooker.Payload;
import com.elypia.webhooker.annotation.*;

import java.util.Map;

@Mapping("twitch")
public class TwitchReceiver extends Receiver {

    private final TwitchNotifier notifier;

    public TwitchReceiver(TwitchNotifier notifier) {
        this.notifier = notifier;
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
    public void onFollowEvent(Payload payload, FollowEvent event) {
        notifier.onFollowEvent(event);
    }

    @Mapping("stream_changed")
    public void onStreamChanged(Payload payload, StreamUpdateEvent event) {
        notifier.onStreamChangedEvent(event);
    }
}
