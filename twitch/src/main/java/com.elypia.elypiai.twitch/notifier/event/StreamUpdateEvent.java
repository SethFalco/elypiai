package com.elypia.elypiai.twitch.notifier.event;

import com.elypia.elypiai.twitch.entity.Stream;

public class StreamUpdateEvent {

    /**
     * The new stream data, or null if the
     * user went offline.
     */
    private Stream stream;

    public StreamUpdateEvent(Stream stream) {
        this.stream = stream;
    }

    public Stream getStream() {
        return stream;
    }

    public boolean isLive() {
        return stream != null;
    }
}
