/*
 * Copyright 2019-2019 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.elypiai.twitch.notifier.dispatchers;

import org.elypia.webhooker.*;
import org.slf4j.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class AuthDispatcher implements Dispatcher {

    private static Logger logger = LoggerFactory.getLogger(AuthDispatcher.class);

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

        logger.info("Received authentication challenge from Twitch, wont continue with dispatchers.");
        payload.getResponse().body(challenge);
        return false;
    }
}
