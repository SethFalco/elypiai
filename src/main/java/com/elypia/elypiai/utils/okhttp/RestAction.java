package com.elypia.elypiai.utils.okhttp;

import java.io.IOException;
import java.util.function.Consumer;

public abstract class RestAction<T> {

    private static Consumer<IOException> defaultFailure = IOException::printStackTrace;

    public void queue() {
        queue(null);
    }

    public void queue(Consumer<T> success) {
        queue(success, null);
    }

    public void queue(Consumer<T> success, Consumer<IOException> ex) {
        
    }

    public T complete() {
        return null;
    }
}
