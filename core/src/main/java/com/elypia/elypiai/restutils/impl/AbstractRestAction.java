package com.elypia.elypiai.restutils.impl;

import java.util.function.Consumer;

public abstract class AbstractRestAction<T> implements IRestAction<T> {

    @Override
    public void queue() {
        queue(null);
    }

    @Override
    public void queue(Consumer<T> success) {
        queue(success, DEFAULT_FAILURE);
    }
}
