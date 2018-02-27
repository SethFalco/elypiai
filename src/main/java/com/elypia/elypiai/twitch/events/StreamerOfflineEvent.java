package com.elypia.elypiai.twitch.events;

import com.elypia.elypiai.twitch.TwitchStream;
import com.elypia.elypiai.twitch.TwitchUser;

public class StreamerOfflineEvent {

	private TwitchUser user;
	private TwitchStream stream;

	public StreamerOfflineEvent(TwitchUser user) {
		this.user = user;
	}

	public TwitchUser getUser() {
		return user;
	}

	public TwitchStream getSteam() {
		return stream;
	}
}
