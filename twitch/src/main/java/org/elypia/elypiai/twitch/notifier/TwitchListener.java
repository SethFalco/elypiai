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

package org.elypia.elypiai.twitch.notifier;

import org.elypia.elypiai.twitch.notifier.event.*;

/**
 * All get these events are based on your Twitch Webhook
 * subscriptions, you'll only be notified get events that
 * you've subsribed to.
 *
 * @author seth@elypia.org (Syed Shah)
 */
public interface TwitchListener {

    /**
     * When a user follows another user.
     *
     * @param event Information about a follow event on Twitch.
     */
    void onFollowEvent(FollowEvent event);

    /**
     * When the stream information changes.
     *
     * @param event Information on the stream event,
     *              the stream is null if the user went offline.
     */
    void onStreamUpdateEvent(StreamUpdateEvent event);

    /**
     * When a users information changes.
     * @param event Information on a user's profile update, only includes new data.
     */
    void onUserUpdateEvent(UserUpdatedEvent event);

    void onGameAnalyticsEvent(GameAnalyticsEvent event);
    void onExtensionAnalytics(ExtensionAnalyticsEvent event);
}
