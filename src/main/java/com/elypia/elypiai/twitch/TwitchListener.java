package com.elypia.elypiai.twitch;

import com.elypia.elypiai.twitch.events.StreamerLiveEvent;
import com.elypia.elypiai.twitch.events.StreamerOfflineEvent;

public interface TwitchListener {
	void onStreamerLive(StreamerLiveEvent event);
	void onStreamerOffline(StreamerOfflineEvent event);
}
