package com.elypia.elypiai.twitch.events;

import com.elypia.elypiai.twitch.TwitchStream;
import com.elypia.elypiai.twitch.TwitchUser;

public class StreamerLiveEvent {

	private TwitchUser user;
	private TwitchStream stream;

	public StreamerLiveEvent(TwitchUser user) {
		this.user = user;
		this.stream = user.getStream();
	}

	public TwitchUser getUser() {
		return user;
	}

	public TwitchStream getSteam() {
		return stream;
	}
}
