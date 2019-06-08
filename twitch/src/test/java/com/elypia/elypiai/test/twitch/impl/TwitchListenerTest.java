package com.elypia.elypiai.test.twitch.impl;

import com.elypia.elypiai.twitch.notifier.TwitchListener;
import com.elypia.elypiai.twitch.notifier.event.*;

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
