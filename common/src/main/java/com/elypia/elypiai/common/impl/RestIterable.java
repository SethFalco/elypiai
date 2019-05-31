package com.elypia.elypiai.common.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface RestIterable<T> {

    void queue(Consumer<List<Optional<T>>> success, Consumer<Throwable> ex);
    void cancel();

    default void queue() {
        queue(null);
    }

    default void queue(Consumer<List<Optional<T>>> success) {
        queue(success, RestInterface.DEFAULT_FAILURE);
    }
}
