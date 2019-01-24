package com.elypia.elypiai.twitch.notifier.event;

import com.elypia.elypiai.twitch.entity.Stream;

public class StreamUpdateEvent {

    /**
     * Is the streamer now online or offline.
     */
    private boolean isLive;

    /**
     * The new stream data, or null if the
     * user wen't offline.
     */
    private Stream stream;

    public boolean isLive() {
        return isLive;
    }

    public Stream getStream() {
        return stream;
    }
}
