package com.elypia.elypiai.twitch.notifier.event;

import com.elypia.elypiai.twitch.entity.GameAnalytics;

import java.util.List;

public class GameAnalyticsEvent {

    private List<GameAnalytics> gameAnalytics;

    public List<GameAnalytics> getGameAnalytics() {
        return gameAnalytics;
    }
}
