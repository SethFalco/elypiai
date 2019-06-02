package com.elypia.elypiai.common.core;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

public interface RestInterface<T> {

    Consumer<Throwable> DEFAULT_FAILURE = Throwable::printStackTrace;

    void queue(Consumer<Optional<T>> success, Consumer<Throwable> ex);
    Optional<T> complete() throws IOException;
    void cancel();

    default void queue() {
        queue(null);
    }

    default void queue(Consumer<Optional<T>> success) {
        queue(success, DEFAULT_FAILURE);
    }

    /**
     * It is strongly recommended to use {@link #complete()}
     * instead of this and use {@link Optional} for better checking.
     *
     * @return The request body or null.
     */
    default T completeGet() throws IOException {
        return complete().orElse(null);
    }
}
