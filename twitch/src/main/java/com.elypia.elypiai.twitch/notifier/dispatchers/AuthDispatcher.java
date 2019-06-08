package com.elypia.elypiai.twitch.notifier.dispatchers;

import com.elypia.webhooker.*;

public class AuthDispatcher implements Dispatcher {

    /**
     * If <code>hub.challenge</code> exists, this is an authentication
     * or verification payload from Twitch which is sent
     * immeditately upon registering the webhook and not an actual
     * event. This completes the challenge that is sent.
     *
     * @param payload The client, request, response of this event.
     * @return If to continue with other dispatchers.
     */
    @Override
    public boolean dispatch(Payload payload) {
        String challenge = payload.getRequest().params("hub.challenge");

        if (challenge == null)
            return true;

        payload.getResponse().body(challenge);
        return false;
    }
}
