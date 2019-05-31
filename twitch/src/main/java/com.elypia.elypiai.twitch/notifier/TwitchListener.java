package com.elypia.elypiai.twitch.notifier;

import com.elypia.elypiai.twitch.notifier.event.*;

/**
 * All get these events are based on your Twitch Webhook
 * subscriptions, you'll only be notified get events that
 * you've subsribed to.
 */
public interface TwitchListener {

    /**
     * When a user follows another user.
     *
     * @param event
     */
    void onFollowEvent(FollowEvent event);

    /**
     * When the stream information changes.
     *
     * @param event
     */
    void onStreamUpdateEvent(StreamUpdateEvent event);

    /**
     * When a users information changes.
     * @param event
     */
    void onUserUpdateEvent(UserUpdatedEvent event);

    void onGameAnalyticsEvent(GameAnalyticsEvent event);
    void onExtensionAnalytics(ExtensionAnalyticsEvent event);
}
