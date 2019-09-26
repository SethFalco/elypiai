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

package org.elypia.elypiai.twitch.impl;

import org.elypia.elypiai.twitch.notifier.TwitchListener;
import org.elypia.elypiai.twitch.notifier.event.*;

/**
 * @author seth@elypia.org (Syed Shah)
 */
public class TwitchListenerTest implements TwitchListener {

    private FollowEvent followEvent;
    private StreamUpdateEvent streamEvent;
    private UserUpdatedEvent userEvent;
    private GameAnalyticsEvent gameAnalyticsEvent;
    private ExtensionAnalyticsEvent extensionAnalyticsEvent;

    @Override
    public void onFollowEvent(FollowEvent event) {
        this.followEvent = event;
    }

    @Override
    public void onStreamUpdateEvent(StreamUpdateEvent event) {
        this.streamEvent = event;
    }

    @Override
    public void onUserUpdateEvent(UserUpdatedEvent event) {
        this.userEvent = event;
    }

    @Override
    public void onGameAnalyticsEvent(GameAnalyticsEvent event) {
        this.gameAnalyticsEvent = event;
    }

    @Override
    public void onExtensionAnalytics(ExtensionAnalyticsEvent event) {
        this.extensionAnalyticsEvent = event;
    }

    public FollowEvent getFollowEvent() {
        return followEvent;
    }

    public StreamUpdateEvent getStreamEvent() {
        return streamEvent;
    }

    public UserUpdatedEvent getUserEvent() {
        return userEvent;
    }

    public GameAnalyticsEvent getGameAnalyticsEvent() {
        return gameAnalyticsEvent;
    }

    public ExtensionAnalyticsEvent getExtensionAnalyticsEvent() {
        return extensionAnalyticsEvent;
    }
}
